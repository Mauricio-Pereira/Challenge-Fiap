package fiap.tds.repositories;

import fiap.tds.entities.Conta.Conta;
import fiap.tds.entities.Conta.ContaCorrente;
import fiap.tds.entities.Conta.ContaPoupanca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ContaRepository {

    public static final Map<String, String> CONNECTION_PROPERTIES = Map.of(
            "URL", "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL",
            "USER", "PF1779",
            "PASSWORD", "fiap23"
    );

    public static final String TB_NAME = "CONTA";

    public static final Map<String, String> TB_COLUMNS = Map.of(
            "ID", "ID",
            "NUMERO", "NUMERO",
            "SALDO", "SALDO",
            "CHEQUE_ESPECIAL_HABILITADO", "CHEQUE_ESPECIAL_HABILITADO",
            "TIPO", "TIPO",
            "TAXA", "TAXA"
    );

    public ContaRepository() {
        Initialize();
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                CONNECTION_PROPERTIES.get("URL"),
                CONNECTION_PROPERTIES.get("USER"),
                CONNECTION_PROPERTIES.get("PASSWORD"));
    }

    private void Initialize() {
        try {
            var conn = getConnection();
            var stmt = conn.prepareStatement(
                    ("CREATE TABLE %s (" +
                            "%s NUMBER generated as identity constraint CONTA_PK PRIMARY KEY, " +
                            "%s VARCHAR2(20) NOT NULL, " +
                            "%s NUMBER(10,2) NOT NULL, " +
                            "%s NUMBER(1), " +
                            "%s VARCHAR2(2) NOT NULL, " +
                            "%s NUMBER(10,2))")
                            .formatted(TB_NAME,
                                    TB_COLUMNS.get("ID"),
                                    TB_COLUMNS.get("NUMERO"),
                                    TB_COLUMNS.get("SALDO"),
                                    TB_COLUMNS.get("CHEQUE_ESPECIAL_HABILITADO"),
                                    TB_COLUMNS.get("TIPO"),
                                    TB_COLUMNS.get("TAXA")));
            stmt.executeUpdate();
            System.out.println("Tabela criada com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void shutdown() {
        try {
            var conn = getConnection();
            var stmt = conn.prepareStatement("DROP TABLE %s".formatted(TB_NAME));
            stmt.executeUpdate();
            System.out.println("Tabela excluída com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void create(Conta conta) {
        try {
            var conn = getConnection();
            var stmt = conn.prepareStatement(
                    "INSERT INTO %s(%s, %s, %s, %s, %s) VALUES (?, ?, ?, ?,?)"
                            .formatted(TB_NAME,
                                    TB_COLUMNS.get("NUMERO"),
                                    TB_COLUMNS.get("SALDO"),
                                    TB_COLUMNS.get("CHEQUE_ESPECIAL_HABILITADO"),
                                    TB_COLUMNS.get("TIPO"),
                                    TB_COLUMNS.get("TAXA")));
            stmt.setString(1, conta.getNumero());
            stmt.setDouble(2, conta.getSaldo());
            if (conta instanceof ContaCorrente) {
                stmt.setBoolean(3,
                        ((ContaCorrente) conta).isChequeEspecialHabilitado());
                stmt.setString(4, "CC");
                stmt.setNull(5, Types.DOUBLE);
            } else if (conta instanceof ContaPoupanca) {
                stmt.setNull(3, Types.NUMERIC);
                stmt.setString(4, "CP");
                stmt.setDouble(5, ((ContaPoupanca) conta).getTaxa());
            }
            var result = stmt.executeUpdate();
            System.out.println("Conta criada com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Conta> readAll() {
        var contas = new ArrayList<Conta>();
        try {
            var conn = getConnection();
            var stmt = conn.prepareStatement("SELECT * FROM %s".formatted(TB_NAME));
            var resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString(TB_COLUMNS.get("TIPO")).equals("CC")) {
                    contas.add(new ContaCorrente(
                            resultSet.getInt(TB_COLUMNS.get("ID")),
                            resultSet.getString(TB_COLUMNS.get("NUMERO")),
                            resultSet.getDouble(TB_COLUMNS.get("SALDO")),
                            resultSet.getBoolean(TB_COLUMNS.get("CHEQUE_ESPECIAL_HABILITADO"))
                    ));
                } else {
                    contas.add(new ContaPoupanca(
                            resultSet.getInt(TB_COLUMNS.get("ID")),
                            resultSet.getString(TB_COLUMNS.get("NUMERO")),
                            resultSet.getDouble(TB_COLUMNS.get("SALDO")),
                            resultSet.getDouble(TB_COLUMNS.get("TAXA"))
                            ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contas;
    }
}
