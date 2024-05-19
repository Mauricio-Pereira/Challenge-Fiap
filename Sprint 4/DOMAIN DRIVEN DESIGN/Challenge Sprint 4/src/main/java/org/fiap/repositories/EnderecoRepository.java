package org.fiap.repositories;

import org.fiap.connection.DatabaseConnection;
import org.fiap.entities.Endereco;
import org.fiap.entities._BaseEntity;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EnderecoRepository extends _BaseRepositoryImpl<Endereco>{

    DatabaseConnection connection = new DatabaseConnection();
    public static final String TB_NAME = "CH_ENDERECO";

    public static final Map<String, String> TB_COLUMNS_ENDERECO = Map.of(
            "COD_ENDERECO", "COD_ENDERECO",
            "LOGRADOURO", "LOGRADOURO",
            "NUMERO", "NUMERO",
            "COMPLEMENTO", "COMPLEMENTO",
            "CEP", "CEP",
            "CH_CLIENTE_COD_CLIENTE", "CH_CLIENTE_COD_CLIENTE",
            "CH_BAIRRO_COD_BAIRRO", "CH_BAIRRO_COD_BAIRRO"
    );

    public static final Map<String, String> TB_COLUMNS_BAIRRO = Map.of(
            "COD_BAIRRO", "COD_BAIRRO",
            "NOME", "NOME",
            "CH_CIDADE_COD_CIDADE", "CH_CIDADE_COD_CIDADE"
    );



    public EnderecoRepository() {
        super(Endereco.class);
        Initialize();
    }

    private void Initialize() {
        String sql = "CREATE TABLE %s (" +
                "%s NUMBER generated as idendereco constraint %s_PK PRIMARY KEY, " +
                "%s VARCHAR2(50) NOT NULL, " +
                "%s VARCHAR2(10) NOT NULL, " +
                "%s VARCHAR2(50) NOT NULL, " +
                "%s VARCHAR2(8) NOT NULL, " +
                "%s NUMBER NOT NULL, " +
                "%s NUMBER NOT NULL)"
                        .formatted(TB_NAME,
                                TB_COLUMNS_ENDERECO.get("COD_ENDERECO"),
                                TB_NAME,
                                TB_COLUMNS_ENDERECO.get("LOGRADOURO"),
                                TB_COLUMNS_ENDERECO.get("NUMERO"),
                                TB_COLUMNS_ENDERECO.get("COMPLEMENTO"),
                                TB_COLUMNS_ENDERECO.get("CEP"),
                                TB_COLUMNS_ENDERECO.get("CH_CLIENTE_COD_CLIENTE"),
                                TB_COLUMNS_ENDERECO.get("CH_CIDADE_COD_BAIRRO")
                        );
        try (var conn = connection.getConnection()) {
            var metaData = conn.getMetaData();
            var rs = metaData.getTables(null, null, TB_NAME.toUpperCase(), new String[]{"TABLE"});
            if (!rs.next()) {
                try (var stmt = conn.createStatement()) {
                    stmt.executeUpdate(sql);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        // Outros métodos e atributos

    public void Create(Endereco endereco) {
        Map<Boolean, String> validation = endereco.validate();
        if (validation.containsKey(false)) {
            throw new IllegalArgumentException(validation.get(false));
        }

        // Verificar se o cliente já tem um endereço
        String checkSql = "SELECT COUNT(*) FROM " + TB_NAME + " WHERE " + TB_COLUMNS_ENDERECO.get("CH_CLIENTE_COD_CLIENTE") + " = ?";
        try (var conn = connection.getConnection(); var checkStmt = conn.prepareStatement(checkSql)) {
            checkStmt.setInt(1, endereco.getCliente().getId());
            try (var rs = checkStmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    throw new IllegalArgumentException("Cliente já possui um endereço.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao verificar o endereço do cliente: " + e.getMessage());
        }

        String sql = "INSERT INTO " + TB_NAME + " (" +
                TB_COLUMNS_ENDERECO.get("LOGRADOURO") + ", " +
                TB_COLUMNS_ENDERECO.get("NUMERO") + ", " +
                TB_COLUMNS_ENDERECO.get("COMPLEMENTO") + ", " +
                TB_COLUMNS_ENDERECO.get("CEP") + ", " +
                TB_COLUMNS_ENDERECO.get("CH_CLIENTE_COD_CLIENTE") + ", " +
                TB_COLUMNS_ENDERECO.get("CH_BAIRRO_COD_BAIRRO") + ") " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (var conn = connection.getConnection();
             var stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, endereco.getLogradouro());
            stmt.setInt(2, Integer.parseInt(endereco.getNumero()));
            stmt.setString(3, endereco.getComplemento());
            stmt.setString(4, endereco.getCep()); // Mantendo como string
            stmt.setInt(5, endereco.getCliente().getId());
            stmt.setInt(6, FindBairroId(endereco.getBairro()));

            stmt.executeUpdate();



        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao criar o endereço: " + e.getMessage());
        }
    }




    @Override
    public List<Endereco> ReadAll() {
        List<Endereco> enderecos = new ArrayList<>();
        var sql = "SELECT e.COD_ENDERECO, e.LOGRADOURO, e.NUMERO, e.COMPLEMENTO, e.CEP, " +
                "b.nome AS bairro, c.nome AS cidade, est.nome AS estado, p.nome AS pais " +
                "FROM ch_endereco e " +
                "JOIN ch_bairro b ON e.ch_bairro_cod_bairro = b.cod_bairro " +
                "JOIN ch_cidade c ON b.ch_cidade_cod_cidade = c.cod_cidade " +
                "JOIN ch_estado est ON c.ch_estado_cod_estado = est.cod_estado " +
                "JOIN ch_pais p ON est.ch_pais_cod_pais = p.cod_pais";
        try (var conn = connection.getConnection(); var stmt = conn.prepareStatement(sql)) {
                var rs = stmt.executeQuery();
                while (rs.next()) {
                    var endereco = new Endereco();
                    endereco.setId(rs.getInt("COD_ENDERECO"));
                    endereco.setLogradouro(rs.getString("LOGRADOURO"));
                    endereco.setNumero(rs.getString("NUMERO"));
                    endereco.setComplemento(rs.getString("COMPLEMENTO"));
                    endereco.setCep(rs.getString("CEP"));

                    // Adicionando informações de bairro, cidade, estado e país
                    endereco.setBairro(rs.getString("bairro"));
                    endereco.setCidade(rs.getString("cidade"));
                    endereco.setEstado(rs.getString("estado"));
                    endereco.setPais(rs.getString("pais"));

                    enderecos.add(endereco);
            }
            return enderecos;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public boolean UpdateById(Endereco endereco, int id) {
        Map<Boolean, String> validation = endereco.validate();
        if (validation.containsKey(false)) {
            throw new IllegalArgumentException(validation.get(false));
        }

        // Verificar se o endereço existe
        String checkSql = "SELECT COUNT(*) FROM " + TB_NAME + " WHERE cod_endereco = ?";
        try (var conn = connection.getConnection(); var checkStmt = conn.prepareStatement(checkSql)) {
            checkStmt.setInt(1, id);
            try (var rs = checkStmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) == 0) {
                    throw new IllegalArgumentException("Endereço com ID " + id + " não encontrado.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao verificar o endereço: " + e.getMessage());
        }

        String sql = "UPDATE " + TB_NAME + " SET " +
                TB_COLUMNS_ENDERECO.get("LOGRADOURO") + " = ?, " +
                TB_COLUMNS_ENDERECO.get("NUMERO") + " = ?, " +
                TB_COLUMNS_ENDERECO.get("COMPLEMENTO") + " = ?, " +
                TB_COLUMNS_ENDERECO.get("CEP") + " = ?, " +
                TB_COLUMNS_ENDERECO.get("CH_CLIENTE_COD_CLIENTE") + " = ?, " +
                TB_COLUMNS_ENDERECO.get("CH_BAIRRO_COD_BAIRRO") + " = ? " +
                "WHERE cod_endereco = ?";

        try (var conn = connection.getConnection();
             var stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, endereco.getLogradouro());
            stmt.setInt(2, Integer.parseInt(endereco.getNumero()));
            stmt.setString(3, endereco.getComplemento());
            stmt.setString(4, endereco.getCep());
            stmt.setInt(5, endereco.getCliente().getId());
            stmt.setInt(6, FindBairroId(endereco.getBairro()));
            stmt.setInt(7, id);

            var result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean DeleteById(int id) {
        try (var conn = connection.getConnection(); var stmt = conn.prepareStatement("DELETE FROM " + TB_NAME + " WHERE " + TB_COLUMNS_ENDERECO.get("COD_ENDERECO") + " = ?")) {
            stmt.setInt(1, id);
            var result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        }
}


    @Override
    public Endereco ReadById(int id) {
        Endereco endereco = null;
        var sql = "SELECT e.*, b.nome AS bairro, c.nome AS cidade, est.nome AS estado, p.nome AS pais " +
                "FROM ch_endereco e " +
                "JOIN ch_bairro b ON e.ch_bairro_cod_bairro = b.cod_bairro " +
                "JOIN ch_cidade c ON b.ch_cidade_cod_cidade = c.cod_cidade " +
                "JOIN ch_estado est ON c.ch_estado_cod_estado = est.cod_estado " +
                "JOIN ch_pais p ON est.ch_pais_cod_pais = p.cod_pais " +
                "WHERE e.COD_ENDERECO = ?";
        try (var conn = connection.getConnection(); var stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                endereco = new Endereco();
                endereco.setId(rs.getInt("COD_ENDERECO"));
                endereco.setLogradouro(rs.getString("LOGRADOURO"));
                endereco.setNumero(rs.getString("NUMERO"));
                endereco.setComplemento(rs.getString("COMPLEMENTO"));
                endereco.setCep(rs.getString("CEP"));

                // Adicionando informações de bairro, cidade, estado e país
                endereco.setBairro(rs.getString("bairro"));
                endereco.setCidade(rs.getString("cidade"));
                endereco.setEstado(rs.getString("estado"));
                endereco.setPais(rs.getString("pais"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return endereco;
    }

    public int FindBairroId(String bairro) throws SQLException {
        int codEstado;
        String sql = "SELECT * FROM %s WHERE %s = ?"
                .formatted("CH_BAIRRO", TB_COLUMNS_BAIRRO.get("NOME"));
        try (var conn = connection.getConnection(); var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, bairro);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                codEstado = rs.getInt(TB_COLUMNS_BAIRRO.get("COD_BAIRRO"));
                return codEstado;
            } else {
                throw new SQLException("Bairro não encontrado no banco de dados");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
