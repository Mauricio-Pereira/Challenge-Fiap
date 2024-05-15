package org.fiap.connection;

import com.sun.jdi.connect.spi.Connection;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    private static final String USER = "rm553748";
    private static final String PASSWORD = "291096";

    public static java.sql.Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void closeConnection(Connection connection) throws SQLException, IOException {
        connection.close();
    }
}