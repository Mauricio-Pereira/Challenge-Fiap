package org.fiap.repositories;

import org.fiap.connection.DatabaseConnection;
import org.fiap.entities.Cliente;
import org.fiap.entities.Produto;
import org.fiap.entities._BaseEntity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProdutoRepository extends _BaseRepositoryImpl<Produto>{
    ClienteRepository clienteRepository = new ClienteRepository();
    DatabaseConnection connection = new DatabaseConnection();

    public static final String TB_NAME = "CH_PRODUTO";

    public static final Map<String, String> TB_COLUMNS = Map.of(
            "COD_PRODUTO", "COD_PRODUTO",
            "NOME", "NOME",
            "DESCRICAO", "DESCRICAO",
            "PRECO", "PRECO",
            "ESTOQUE", "ESTOQUE",
            "CH_CLIENTE_COD_CLIENTE", "CH_CLIENTE_COD_CLIENTE"

    );


    public ProdutoRepository() {
        Initialize();
    }

    private void Initialize() {
        String sql = "CREATE TABLE %s (" +
                "%s NUMBER generated as identity constraint %s_PK PRIMARY KEY, " +
                        "%s VARCHAR2(100) NOT NULL, " +
                        "%s VARCHAR2(250) NOT NULL, " +
                        "%s NUMBER NOT NULL, " +
                        "%s NUMBER NOT NULL, ".formatted(TB_NAME,
                                TB_COLUMNS.get("COD_PRODUTO"),
                                TB_NAME,
                                TB_COLUMNS.get("NOME"),
                                TB_COLUMNS.get("DESCRICAO"),
                                TB_COLUMNS.get("PRECO"),
                                TB_COLUMNS.get("ESTOQUE")
                        );

        try (var conn = connection.getConnection()) {
            var metaData = conn.getMetaData();
            var rs = metaData.getTables(null, null, TB_NAME.toUpperCase(), new String[]{"TABLE"});
            if (!rs.next()) {
                try (var stmt = conn.prepareStatement(sql)) {
                    stmt.execute();
                }
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Create(Produto produto, int id) {
        // Verifique se o cliente existe
        Cliente cliente = clienteRepository.ReadById(id);
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente com ID " + id + " não existe.");
        }

        String sql = "INSERT INTO " + TB_NAME + " (" +
                TB_COLUMNS.get("NOME") + ", " +
                TB_COLUMNS.get("DESCRICAO") + ", " +
                TB_COLUMNS.get("PRECO") + ", " +
                TB_COLUMNS.get("ESTOQUE") + ", " +
                TB_COLUMNS.get("CH_CLIENTE_COD_CLIENTE") + ") " +
                "VALUES (?, ?, ?, ?, ?)";

        try (var conn = connection.getConnection()) {
            try (var stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, produto.getNome());
                stmt.setString(2, produto.getDescricao());
                stmt.setDouble(3, produto.getPreco());
                stmt.setInt(4, produto.getEstoque());
                stmt.setInt(5, id);
                var result = stmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        produto.setId(ReadAll().getLast().getId());
    }

    @Override
    public List<Produto> ReadAll() {
        List<Produto> produtos = new ArrayList<>();
        try (var conn = connection.getConnection()){
            var stmt = conn.prepareStatement("SELECT * FROM " + TB_NAME + " ORDER BY "+ TB_COLUMNS.get("COD_PRODUTO")+" ASC");
            var rs = stmt.executeQuery();
            while (rs.next()){
                Produto produto = new Produto(
                        rs.getInt(TB_COLUMNS.get("COD_PRODUTO")),
                        clienteRepository.ReadById(rs.getInt(TB_COLUMNS.get("CH_CLIENTE_COD_CLIENTE"))),
                        rs.getString(TB_COLUMNS.get("NOME")),
                        rs.getString(TB_COLUMNS.get("DESCRICAO")),
                        rs.getDouble(TB_COLUMNS.get("PRECO")),
                        rs.getInt(TB_COLUMNS.get("ESTOQUE"))
                );
                produtos.add(produto);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return produtos;
    }

    @Override
    public boolean UpdateById(Produto produto, int id) {
        String sql = "UPDATE " + TB_NAME + " SET " +
                TB_COLUMNS.get("NOME") + " = ?, " +
                TB_COLUMNS.get("DESCRICAO") + " = ?, " +
                TB_COLUMNS.get("PRECO") + " = ?, " +
                TB_COLUMNS.get("ESTOQUE") + " = ? " +
                "WHERE " + TB_COLUMNS.get("COD_PRODUTO") + " = ?";

        try (var conn = connection.getConnection(); var stmt = conn.prepareStatement(sql)){
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setDouble(3, produto.getPreco());
            stmt.setInt(4, produto.getEstoque());
            stmt.setInt(5, id);
            var result = stmt.executeUpdate();
            return result > 0;
        }catch (SQLException e){
            e.printStackTrace();
        return false;
        }
    }


    @Override
    public Produto ReadById(int id) {
        Produto produto = null;
        try (var conn = connection.getConnection()){
            var stmt = conn.prepareStatement("SELECT * FROM " + TB_NAME + " WHERE " + TB_COLUMNS.get("COD_PRODUTO") + " = ?");
            stmt.setInt(1, id);
            var rs = stmt.executeQuery();
            if (rs.next()){
                produto = new Produto(
                        rs.getInt(TB_COLUMNS.get("COD_PRODUTO")),
                        clienteRepository.ReadById(rs.getInt(TB_COLUMNS.get("CH_CLIENTE_COD_CLIENTE"))),
                        rs.getString(TB_COLUMNS.get("NOME")),
                        rs.getString(TB_COLUMNS.get("DESCRICAO")),
                        rs.getDouble(TB_COLUMNS.get("PRECO")),
                        rs.getInt(TB_COLUMNS.get("ESTOQUE"))
                );
            }
        } catch (SQLException e){
                e.printStackTrace();
        }

        return produto;
    }
    @Override
    public boolean DeleteById(int id) {
        try(var conn = connection.getConnection()){
            var stmt = conn.prepareStatement("DELETE FROM " + TB_NAME + " WHERE " + TB_COLUMNS.get("COD_PRODUTO") + " = ?");
            stmt.setInt(1, id);
            var result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }




}
