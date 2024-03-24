package org.fiap.repositories;

import org.fiap.connection.DatabaseConnection;
import org.fiap.entities.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.sql.DriverManager.getConnection;

public class ClienteRepository extends _BaseRepositoryImpl<Cliente> {

    DatabaseConnection connection = new DatabaseConnection();

    public static final String TB_NAME = "CH_CLIENTE";

    public static final Map<String, String> TB_COLUMNS = Map.of(
            "COD_CLIENTE", "COD_CLIENTE",
            "NOME", "NOME",
            "SOBRENOME", "SOBRENOME",
            "DATA_NASCIMENTO", "DATA_NASCIMENTO",
            "TELEFONE", "TELEFONE",
            "EMAIL_CORPORATIVO", "EMAIL_CORPORATIVO",
            "NOME_USUARIO", "NOME_USUARIO",
            "SENHA", "SENHA"
    );

    public ClienteRepository() {
        Initialize();
    }



    private void Initialize() {
        String sql = "CREATE TABLE %s (" +
                "%s NUMBER generated as identity constraint %s_PK PRIMARY KEY, " +
                "%s VARCHAR2(50) NOT NULL, " +
                "%s VARCHAR2(80) NOT NULL, " +
                "%s DATE NOT NULL, " +
                "%s VARCHAR2(14) NOT NULL, " +
                "%s VARCHAR2(50) NOT NULL, " +
                "%s VARCHAR2(20) NOT NULL, " +
                "%s VARCHAR2(50) NOT NULL)"
                        .formatted(TB_NAME,
                                TB_COLUMNS.get("COD_CLIENTE"),
                                TB_NAME,
                                TB_COLUMNS.get("NOME"),
                                TB_COLUMNS.get("SOBRENOME"),
                                TB_COLUMNS.get("DATA_NASCIMENTO"),
                                TB_COLUMNS.get("TELEFONE"),
                                TB_COLUMNS.get("EMAIL_CORPORATIVO"),
                                TB_COLUMNS.get("NOME_USUARIO"),
                                TB_COLUMNS.get("SENHA")
                        );
        try (var conn = connection.getConnection()) {
            var metaData = conn.getMetaData();
            var rs = metaData.getTables(null, null, TB_NAME.toUpperCase(), new String[]{"TABLE"});
            if (!rs.next()) {
                try(var stmt = conn.prepareStatement(sql)) {
                    stmt.execute();
                }
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void Create(Cliente entity) {
        String sql = "INSERT INTO " + TB_NAME + " (" +
                TB_COLUMNS.get("NOME") + ", " +
                TB_COLUMNS.get("SOBRENOME") + ", " +
                TB_COLUMNS.get("DATA_NASCIMENTO") + ", " +
                TB_COLUMNS.get("TELEFONE") + ", " +
                TB_COLUMNS.get("EMAIL_CORPORATIVO") + ", " +
                TB_COLUMNS.get("NOME_USUARIO") + ", " +
                TB_COLUMNS.get("SENHA") + ") " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (var conn = connection.getConnection(); var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, entity.getNome());
            stmt.setString(2, entity.getSobrenome());
            stmt.setDate(3, java.sql.Date.valueOf(entity.getDataNascimento()));
            stmt.setString(4, entity.getTelefone());
            stmt.setString(5, entity.getEmail());
            stmt.setString(6, entity.getUsuario());
            stmt.setString(7, entity.getSenha());
            var result = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Cliente> ReadAll() {
        List<Cliente> clientes = new ArrayList<>();
        try (var conn = connection.getConnection()) {
            var stmt = conn.prepareStatement("SELECT * FROM " + TB_NAME + " ORDER BY "+ TB_COLUMNS.get("COD_CLIENTE")+" ASC");
            var rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt(TB_COLUMNS.get("COD_CLIENTE")),
                        rs.getString(TB_COLUMNS.get("NOME")),
                        rs.getString(TB_COLUMNS.get("SOBRENOME")),
                        rs.getDate(TB_COLUMNS.get("DATA_NASCIMENTO")).toLocalDate(),
                        rs.getString(TB_COLUMNS.get("TELEFONE")),
                        rs.getString(TB_COLUMNS.get("EMAIL_CORPORATIVO")),
                        rs.getString(TB_COLUMNS.get("NOME_USUARIO")),
                        rs.getString(TB_COLUMNS.get("SENHA"))
                );
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    public boolean UpdateById(Cliente entity, int id) {
        String sql = "UPDATE " + TB_NAME + " SET " +
                TB_COLUMNS.get("NOME") + " = ?, " +
                TB_COLUMNS.get("SOBRENOME") + " = ?, " +
                TB_COLUMNS.get("DATA_NASCIMENTO") + " = ?, " +
                TB_COLUMNS.get("TELEFONE") + " = ?, " +
                TB_COLUMNS.get("EMAIL_CORPORATIVO") + " = ?, " +
                TB_COLUMNS.get("NOME_USUARIO") + " = ?, " +
                TB_COLUMNS.get("SENHA") + " = ? " +
                "WHERE " + TB_COLUMNS.get("COD_CLIENTE") + " = ?";

        try (var conn = connection.getConnection(); var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, entity.getNome());
            stmt.setString(2, entity.getSobrenome());
            stmt.setDate(3, java.sql.Date.valueOf(entity.getDataNascimento()));
            stmt.setString(4, entity.getTelefone());
            stmt.setString(5, entity.getEmail());
            stmt.setString(6, entity.getUsuario());
            stmt.setString(7, entity.getSenha());
            stmt.setInt(8, id);
            var result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Cliente ReadById(int id) {
        Cliente cliente = null;
        try (var conn = connection.getConnection()) {
            var stmt = conn.prepareStatement("SELECT * FROM " + TB_NAME + " WHERE " + TB_COLUMNS.get("COD_CLIENTE") + " = ?");
            stmt.setInt(1, id);
            try (var rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cliente = new Cliente(
                            rs.getInt(TB_COLUMNS.get("COD_CLIENTE")),
                            rs.getString(TB_COLUMNS.get("NOME")),
                            rs.getString(TB_COLUMNS.get("SOBRENOME")),
                            rs.getDate(TB_COLUMNS.get("DATA_NASCIMENTO")).toLocalDate(),
                            rs.getString(TB_COLUMNS.get("TELEFONE")),
                            rs.getString(TB_COLUMNS.get("EMAIL_CORPORATIVO")),
                            rs.getString(TB_COLUMNS.get("NOME_USUARIO")),
                            rs.getString(TB_COLUMNS.get("SENHA"))
                    );
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }

    public boolean DeleteById(int id) {
        try (var conn = connection.getConnection()) {
            var stmt = conn.prepareStatement("DELETE FROM " + TB_NAME + " WHERE " + TB_COLUMNS.get("COD_CLIENTE") + " = ?");
            stmt.setInt(1, id);
            var result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }




}
