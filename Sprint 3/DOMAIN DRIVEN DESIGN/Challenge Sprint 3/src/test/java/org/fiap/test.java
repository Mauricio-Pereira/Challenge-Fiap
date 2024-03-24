package org.fiap;

import jakarta.ws.rs.client.Client;
import org.fiap.entities.Cliente;
import org.fiap.entities.Endereco;
import org.fiap.entities.Produto;
import org.fiap.repositories.ClienteRepository;

import java.time.LocalDate;

public class test {
    public static void main(String[] args) {

        ClienteRepository clienteRepository = new ClienteRepository();
        // Criando o primeiro cliente
        Cliente cliente1 = new Cliente();
        cliente1.setId(1);
        cliente1.setNome("Cliente 1");
        cliente1.setSobrenome("Sobrenome 1");
        cliente1.setDataNascimento("01/01/2000");
        cliente1.setTelefone("11999999999");
        cliente1.setEmail("cliente1@email.com");
        cliente1.setUsuario("usuario1");
        cliente1.setSenha("Senha@123");

        // Criando o segundo cliente
        Cliente cliente2 = new Cliente();
        cliente2.setId(2);
        cliente2.setNome("Cliente 2");
        cliente2.setSobrenome("Sobrenome 2");
        cliente2.setDataNascimento("02/02/2000");
        cliente2.setTelefone("11999999998");
        cliente2.setEmail("cliente2@email.com");
        cliente2.setUsuario("usuario2");
        cliente2.setSenha("Senha@123");

        //Criando o terceiro cliente
        Cliente cliente3 = new Cliente();
        cliente3.setId(3);
        cliente3.setNome("Cliente 3");
        cliente3.setSobrenome("Sobrenome 3");
        cliente3.setDataNascimento("03/03/2000");
        cliente3.setTelefone("11999999997");
        cliente3.setEmail("cliente3@email.com");
        cliente3.setUsuario("usuario3");
        cliente3.setSenha("Senha@123");
        clienteRepository.Create(cliente3);

        cliente3.setNome("Joao");
        clienteRepository.Update(cliente3);
        // Adicionando os clientes ao repositório
        clienteRepository.ReadAll();
        clienteRepository.DeleteById(4);
        // Listando os clientes
        clienteRepository.ReadAll();
        System.out.println(clienteRepository.ReadById(4));


    }
}
