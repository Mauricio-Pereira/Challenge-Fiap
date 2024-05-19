# Projeto FIAP

Este projeto é uma aplicação Java que utiliza SQL e Maven. 
Ele foi desenvolvido no IntelliJ IDEA 2023.3.3 e é executado em um sistema operacional Windows.

## Descrição

O projeto consiste em um sistema de gerenciamento de clientes, produtos e endereços. Ele permite criar, ler, atualizar e deletar (CRUD) informações sobre clientes, produtos e endereços. Além disso, o projeto também possui recursos (Resources) para facilitar a conexão com o front-end.

## Tecnologias

- Java: Linguagem de programação usada para desenvolver a aplicação.
- SQL: Linguagem de consulta estruturada usada para interagir com o banco de dados.
- Maven: Ferramenta de automação de compilação usada para gerenciar as dependências do projeto.



## ResetDatabase

A classe `ResetDatabase` está localizada no pacote `org.fiap.connection`. Esta classe é responsável por redefinir o banco de dados e popular as tabelas de países, estados, cidades e bairros. 

Ela foi criada para lidar com a geração automática de IDs, que pode causar problemas durante os testes. A classe lê um arquivo `.sql` e executa cada comando SQL individualmente para redefinir o banco de dados.

Por favor, note que esta classe é usada apenas durante a fase inicial de testes. Ela lê o arquivo `SQL/FIAP.sql` e executa os comandos SQL contidos nele para redefinir o banco de dados.

Aqui está um exemplo de como a classe é usada:

```java
ResetDatabase resetDatabase = new ResetDatabase();
```

Ao instanciar a classe, o construtor é chamado e o banco de dados é redefinido.
```

Lembre-se de que a documentação é uma parte crucial de qualquer projeto de software. Ela ajuda outros desenvolvedores a entenderem o propósito e a funcionalidade de cada parte do código.
## Configuração do Banco de Dados

Para configurar a conexão com o banco de dados, você precisará alterar as configurações no arquivo `DatabaseConnection.java` localizado em `src/main/java/org/fiap/connection/`.

```java
package org.fiap.connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    public static java.sql.Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
```

Neste arquivo, você deve substituir os valores das constantes `URL`, `USER` e `PASSWORD` pelos detalhes do seu banco de dados.

## Como Executar

Para executar o projeto, siga estas etapas:

1. Clone o repositório do GitHub.
2. Abra o projeto no IntelliJ IDEA.
3. Atualize as configurações do banco de dados no arquivo `DatabaseConnection.java`.
4. Execute o arquivo `Test.java`.
5. Execute o arquivo `Main.java`.
5. Importe o arquivo `Challenge Salesforce - Sprint 4.postman_collection.json` no Postman para testar as rotas.
6. Execute a coleção de rotas no Postman.

## Contribuição

As contribuições são bem-vindas. Para contribuir, siga estas etapas:

1. Faça um fork do repositório.
2. Crie uma nova branch com suas alterações.
3. Faça um pull request.

## Integrantes

Maurício Pereira     - Github: https://github.com/Mauricio-Pereira  
Luis Otávio Leitão   - Github: https://github.com/Luiz1614  
Vitor Onofre Ramos   - Github: https://github.com/VitorOnofreRamos
```