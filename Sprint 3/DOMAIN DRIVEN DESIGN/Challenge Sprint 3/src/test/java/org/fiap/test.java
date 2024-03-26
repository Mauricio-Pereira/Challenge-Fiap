package org.fiap;

import jakarta.ws.rs.client.Client;
import org.fiap.entities.Cliente;
import org.fiap.entities.Endereco;
import org.fiap.entities.Produto;
import org.fiap.repositories.ClienteRepository;
import org.fiap.repositories.EnderecoRepository;
import org.fiap.repositories.ProdutoRepository;

import java.sql.SQLException;
import java.time.LocalDate;

public class test {
    public static void main(String[] args) throws SQLException {

        ProdutoRepository produtoRepository = new ProdutoRepository();
        Cliente cliente1 = new Cliente();
        Endereco endereco1 = new Endereco();
        ClienteRepository clienteRepository = new ClienteRepository();
        EnderecoRepository enderecoRepository = new EnderecoRepository();

        // Criando o primeiro cliente
        cliente1.setId(1);
        cliente1.setNome("Cliente 1");
        cliente1.setSobrenome("Sobrenome 1");
        cliente1.setDataNascimento("01/01/2000");
        cliente1.setTelefone("11999999999");
        cliente1.setEmail("cliente1@email.com");
        cliente1.setUsuario("usuario1");
        cliente1.setSenha("Senha@123");
        //clienteRepository.Create(cliente1);
        //Criando endereco
        endereco1.setCep("12345678");
        endereco1.setLogradouro("Rua 1");
        endereco1.setNumero("123");
        endereco1.setComplemento("Complemento 1");
        endereco1.setBairro("Casa Verde");
        endereco1.setCliente(cliente1);
        enderecoRepository.UpdateById(endereco1, 7);
        System.out.println(enderecoRepository.ReadAll());
//        Produto produto1 = new Produto();
//        produto1.setCliente(cliente1);
//        produto1.setNome("Produto 1");
//        produto1.setDescricao("Descrição 1");
//        produto1.setPreco(10.0);
//        produto1.setEstoque(100);
//        produtoRepository.Create(produto1, 4);

//        Produto produto2 = new Produto();
//        produto2.setCliente(cliente1);
//        produto2.setNome("Produto 2");
//        produto2.setDescricao("Descrição 2");
//        produto2.setPreco(20.0);
//        produto2.setEstoque(200);
////        produtoRepository.UpdateById(produto2, 3);
//        produtoRepository.DeleteById(3);
//        System.out.println(clienteRepository);

//
//        // Criando o segundo cliente
//        Cliente cliente1 = new Cliente();
//        cliente1.setId(2);
//        cliente1.setNome("Cliente 2");
//        cliente1.setSobrenome("Sobrenome 2");
//        cliente1.setDataNascimento("02/02/2000");
//        cliente1.setTelefone("11999999998");
//        cliente1.setEmail("cliente1@email.com");
//        cliente1.setUsuario("usuario2");
//        cliente1.setSenha("Senha@123");
//        clienteRepository.Create(cliente1);
//
//        //Criando o terceiro cliente
//        Cliente cliente3 = new Cliente();
//        cliente3.setId(3);
//        cliente3.setNome("Cliente 3");
//        cliente3.setSobrenome("Sobrenome 3");
//        cliente3.setDataNascimento("03/03/2000");
//        cliente3.setTelefone("11999999997");
//        cliente3.setEmail("cliente3@email.com");
//        cliente3.setUsuario("usuario3");
//        cliente3.setSenha("Senha@123");
//        clienteRepository.Create(cliente3);
//
//        cliente3.setNome("Joao");
//
//
//        clienteRepository.ReadAll();




    }
}
