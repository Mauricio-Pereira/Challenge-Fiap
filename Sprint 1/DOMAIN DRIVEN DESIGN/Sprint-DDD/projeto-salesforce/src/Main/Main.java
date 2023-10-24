package Main;

import PackagePrincipal.Cliente;
import PackagePrincipal.Empresa;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        var listaCliente = new ArrayList<Cliente>();
        var listaEmpresa = new ArrayList<Empresa>();


        while(true){
            System.out.println("\r\nBem vindo ao sistema de cadastro de clientes: \r\n " +
                    "digite a opção desejada:\r\n" +
                    "============================ \r\n"+
                    "1. Cadastrar Cliente\r\n" +
                    "2. Cadastrar empresa\r\n" +
                    "3. Consultar Clientes\r\n" +
                    "4. Consultar Empresa\r\n" +
                    "0. Sair");

            var scanner = new Scanner(System.in);
            var opcao = scanner.nextInt();
            scanner.nextLine();

            if(opcao == 0) break;
            else if (opcao == 1) {
                var cliente = new Cliente();

                System.out.println("Digite o nome do Cliente: ");
                cliente.setNome(scanner.nextLine());

                System.out.println("Digite o genero do cliente: ");
                cliente.setGenero(scanner.nextLine());

                System.out.println("Digite o telefone do cliente: ");
                cliente.setTelefone(scanner.nextLine());

                System.out.println("Digite o email do cliente: ");
                cliente.setEmail(scanner.nextLine());

                System.out.println("Digite o endereço do cliente: ");
                cliente.setEndereco(scanner.nextLine());

                System.out.println("Digite a data de nascimento do cliente (somente números): ");
                cliente.setDataNascimento(scanner.nextInt());



                cliente.setStatus(true);

                listaCliente.add(cliente);
            }
            else if (opcao == 2) {

                var empresa = new Empresa();

                System.out.println("Digite o nome do Cliente: ");
                empresa.setCliente(scanner.nextLine());

                System.out.println("Digite o nome da empresa: ");
                empresa.setNomeEmpresa(scanner.nextLine());

                System.out.println("Digite o setor da empresa: ");
                empresa.setSetor(scanner.nextLine());

                System.out.println("Digite o contato da empresa: ");
                empresa.setContato(scanner.nextLine());

                System.out.println("Digite o endereço da empresa: ");
                empresa.setEndereco(scanner.nextLine());

                listaEmpresa.add(empresa);
            } else if (opcao == 3) {
                System.out.println("Clientes Cadastrados: \r\n");
                System.out.println(listaCliente);

            } else if (opcao == 4) {
                System.out.println("Empresas Cadastradas: \r\n");
                System.out.println(listaEmpresa);

            } else if (opcao == 0) {
                System.out.println("Agradecemos a preferencia");
                break;
            }


        }
    }
}

