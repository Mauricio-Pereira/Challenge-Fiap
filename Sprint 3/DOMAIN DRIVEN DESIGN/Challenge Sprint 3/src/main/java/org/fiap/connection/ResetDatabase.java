package org.fiap.connection;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ResetDatabase {

    //Classe para zerar o banco de dados e popular as tabelas de paises, Estados, Cidades e Bairros, já que os ID são gerados automaticamente e isso pode causar problemas no teste

    DatabaseConnection connection = new DatabaseConnection();

    public ResetDatabase() {
        try {
            // Lê o arquivo .sql como uma string em ISO-8859-1, para manter os acentos nas palavraws
            String sql = new String(Files.readAllBytes(Paths.get("SQL/FIAP.sql")), Charset.forName("ISO-8859-1"));

            // Divide a string SQL em vários comandos
            String[] sqlCommands = sql.split(";");

            // Executa cada comando SQL individualmente
            try (var conn = connection.getConnection(); var stmt = conn.createStatement()) {
                for (String command : sqlCommands) {
                    if (!command.trim().isEmpty()) {
                        stmt.executeUpdate(command);
                    }
                }
                System.out.println("Banco de dados resetado!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

