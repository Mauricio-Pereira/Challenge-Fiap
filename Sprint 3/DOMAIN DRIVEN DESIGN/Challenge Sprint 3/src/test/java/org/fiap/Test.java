package org.fiap;

import org.fiap.connection.ResetDatabase;
import org.fiap.entities.Cliente;
import org.fiap.entities.Endereco;
import org.fiap.entities.Produto;
import org.fiap.repositories.ClienteRepository;
import org.fiap.repositories.EnderecoRepository;
import org.fiap.repositories.ProdutoRepository;

import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws SQLException {


        ResetDatabase resetDatabase = new ResetDatabase();
        ProdutoRepository produtoRepository = new ProdutoRepository();
        ClienteRepository clienteRepository = new ClienteRepository();
        EnderecoRepository enderecoRepository = new EnderecoRepository();

        //Criando Clientes
        Cliente cliente1 = new Cliente();
        cliente1.setNome("João");
        cliente1.setSobrenome("Silva");
        cliente1.setDataNascimento("29/10/1996");
        cliente1.setTelefone("11999999999");
        cliente1.setEmail("joao@email.com");
        cliente1.setUsuario("joao");
        cliente1.setSenha("Senha@123");
        clienteRepository.Create(cliente1);

        Cliente cliente2 = new Cliente();
        cliente2.setNome("Maria");
        cliente2.setSobrenome("Silva");
        cliente2.setDataNascimento("29/10/1990");
        cliente2.setTelefone("11999999999");
        cliente2.setEmail("maria@email.com");
        cliente2.setUsuario("maria");
        cliente2.setSenha("Senha@123");
        clienteRepository.Create(cliente2);

        Cliente cliente3 = new Cliente();
        cliente3.setNome("Jonas");
        cliente3.setSobrenome("Silva");
        cliente3.setDataNascimento("29/10/1980");
        cliente3.setTelefone("11999999999");
        cliente3.setEmail("jonas@email.com");
        cliente3.setUsuario("jonas");
        cliente3.setSenha("Senha@123");
        clienteRepository.Create(cliente3);


        //Atualizando E listando Clientes
        cliente1.setTelefone("35999998888");
        cliente2.setTelefone("35999997777");
        clienteRepository.UpdateById(cliente1,1);
        clienteRepository.UpdateById(cliente2,2);
        System.out.println(clienteRepository.ReadById(1));
        System.out.println(clienteRepository.ReadById(2));
        clienteRepository.DeleteById(2);
        System.out.println(clienteRepository.ReadAll());

        //Criando Produtos
        Produto produto1 = new Produto();
        produto1.setNome("Produto 1");
        produto1.setDescricao("Descrição do Produto 1");
        produto1.setPreco(100.0);
        produto1.setEstoque(10);
        produto1.setCliente(cliente1);
        produtoRepository.Create(produto1,1);
        System.out.println(produtoRepository.ReadById(1));

        Produto produto2 = new Produto();
        produto2.setNome("Produto 2");
        produto2.setDescricao("Descrição do Produto 2");
        produto2.setPreco(200.0);
        produto2.setEstoque(20);
        produto2.setCliente(cliente1);
        produtoRepository.Create(produto2,1);
        System.out.println(produtoRepository.ReadById(2));

        //Atualizando E listando Produtos
        produto1.setEstoque(20);
        produto2.setEstoque(30);
        produtoRepository.UpdateById(produto1,1);
        produtoRepository.UpdateById(produto2,2);
        System.out.println(produtoRepository.ReadAll());
        produtoRepository.DeleteById(2);
        System.out.println(produtoRepository.ReadAll());

        //Criando Endereços
        Endereco endereco1 = new Endereco();
        endereco1.setCep("12345678");
        endereco1.setLogradouro("Rua 1");
        endereco1.setNumero("123");
        endereco1.setComplemento("Ap 13");
        endereco1.setBairro("Casa Verde");
        endereco1.setCliente(cliente1);
        enderecoRepository.Create(endereco1);
        System.out.println(enderecoRepository.ReadById(1));

        Endereco endereco2 = new Endereco();
        endereco2.setCep("87654321");
        endereco2.setLogradouro("Rua 2");
        endereco2.setNumero("321");
        endereco2.setComplemento("Ap 31");
        endereco2.setBairro("Tatuapé");
        endereco2.setCliente(cliente3);
        enderecoRepository.Create(endereco2);
        System.out.println(enderecoRepository.ReadById(2));

        //Atualizando E listando Endereços
        endereco1.setNumero("321");
        endereco2.setNumero("123");
        enderecoRepository.UpdateById(endereco1,1);
        enderecoRepository.UpdateById(endereco2,2);
        System.out.println(enderecoRepository.ReadAll());
        enderecoRepository.DeleteById(2);
        System.out.println(enderecoRepository.ReadAll());













    }
}
