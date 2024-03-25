package org.fiap.repositories;

import org.fiap.connection.DatabaseConnection;
import org.fiap.entities.Endereco;
import org.fiap.entities._BaseEntity;

import java.sql.SQLException;
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
            "CH_CIDADE_COD_BAIRRO", "CH_CIDADE_COD_BAIRRO"
    );

    public static final Map<String, String> TB_COLUMNS_BAIRRO = Map.of(
            "COD_BAIRRO", "COD_BAIRRO",
            "NOME", "NOME",
            "CH_CIDADE_COD_CIDADE", "CH_CIDADE_COD_CIDADE"
    );

    public static final Map<String, String> TB_COLUMNS_CIDADE = Map.of(
            "COD_CIDADE", "COD_CIDADE",
            "NOME", "NOME",
            "CH_ESTADO_COD_ESTADO", "CH_ESTADO_COD_ESTADO"
    );

    public static final Map<String, String> TB_COLUMNS_ESTADO = Map.of(
            "COD_ESTADO", "COD_ESTADO",
            "NOME", "NOME",
            "CH_PAIS_COD_PAIS", "CH_PAIS_COD_PAIS"
    );

    public static final Map<String, String> TB_COLUMNS_PAIS = Map.of(
            "COD_PAIS", "COD_PAIS",
            "NOME", "NOME"
    );

    public EnderecoRepository() {
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

    @Override
    public void Create(Endereco endereco) {
        String sqlEndereco = "INSERT INTO %s (%s, %s, %s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?, ?, ?)"
                .formatted(TB_NAME,
                        TB_COLUMNS_ENDERECO.get("LOGRADOURO"),
                        TB_COLUMNS_ENDERECO.get("NUMERO"),
                        TB_COLUMNS_ENDERECO.get("COMPLEMENTO"),
                        TB_COLUMNS_ENDERECO.get("CEP"),
                        TB_COLUMNS_ENDERECO.get("CH_CLIENTE_COD_CLIENTE"),
                        TB_COLUMNS_ENDERECO.get("CH_CIDADE_COD_BAIRRO")
                );
        String sqlBairro = "INSERT INTO %s (%s, %s) VALUES (?, ?)"
                .formatted("CH_BAIRRO",
                        TB_COLUMNS_BAIRRO.get("NOME"),
                        TB_COLUMNS_BAIRRO.get("CH_CIDADE_COD_CIDADE")
                );
        String sqlCidade = "INSERT INTO %s (%s, %s) VALUES (?, ?)"
                .formatted("CH_CIDADE",
                        TB_COLUMNS_CIDADE.get("NOME"),
                        TB_COLUMNS_CIDADE.get("CH_ESTADO_COD_ESTADO")
                );
        String sqlEstado = "INSERT INTO %s (%s, %s) VALUES (?, ?)"
                .formatted("CH_ESTADO",
                        TB_COLUMNS_ESTADO.get("NOME"),
                        TB_COLUMNS_ESTADO.get("CH_PAIS_COD_PAIS")
                );
        String sqlPais = "INSERT INTO %s (%s) VALUES (?)"
                .formatted("CH_PAIS",
                        TB_COLUMNS_PAIS.get("NOME")
                );

        try (var conn = connection.getConnection()) {
            try (var stmt = conn.prepareStatement(sqlPais)) {
                stmt.setString(1, endereco.getPais());
                stmt.execute();
            }
            try (var stmt = conn.prepareStatement(sqlEstado)) {
                stmt.setString(1, endereco.getEstado());
                stmt.setInt(2, Integer.parseInt(TB_COLUMNS_ESTADO.get("CH_PAIS_COD_PAIS")));
                stmt.execute();
            }
            try (var stmt = conn.prepareStatement(sqlCidade)) {
                stmt.setString(1, endereco.getCidade());
                stmt.setInt(2, Integer.parseInt(TB_COLUMNS_CIDADE.get("CH_ESTADO_COD_ESTADO")));
                stmt.execute();
            }
            try (var stmt = conn.prepareStatement(sqlBairro)) {
                stmt.setString(1, endereco.getBairro());
                stmt.setInt(2, Integer.parseInt(TB_COLUMNS_BAIRRO.get("CH_CIDADE_COD_CIDADE")));
                stmt.execute();
            }
            try (var stmt = conn.prepareStatement(sqlEndereco)) {
                stmt.setString(1, endereco.getLogradouro());
                stmt.setString(2, endereco.getNumero());
                stmt.setString(3, endereco.getComplemento());
                stmt.setString(4, endereco.getCep());
                stmt.setInt(5, endereco.getCliente().getId());
                stmt.setInt(6, Integer.parseInt(TB_COLUMNS_ENDERECO.get("CH_CIDADE_COD_BAIRRO")));
                stmt.execute();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public List<Endereco> ReadAll() {
        return null;
    }

    @Override
    public boolean DeleteById(int id) {
        return false;
    }

    @Override
    public boolean UpdateById(Endereco endereco, int id) {
        return false;
    }

    @Override
    public Endereco ReadById(int id) {
        return null;
    }
}
