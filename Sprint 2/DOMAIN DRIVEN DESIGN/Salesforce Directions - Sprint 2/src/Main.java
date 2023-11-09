import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean showcase = true;
        var usuarios = new ArrayList<Usuario>();
        while (showcase == true){
            Scanner scanner = new Scanner(System.in);
            System.out.print("\r\n##--Menu Salesforce Directions--##\n");
            System.out.print("|------------------------------------------|\n");
            System.out.print("| Opção 1 - Novo Cadastro                  |\n");
            System.out.print("| Opção 2 - Mostrar Usuários Cadastrados   |\n");
            System.out.print("| Opção 3 - Dados completos do Usuário     |\n");
            System.out.print("| Opção 4 - Deletar Usuário                |\n");
            System.out.print("| Opção 5 - Sair                           |\n");
            System.out.print("|------------------------------------------|\n");
            System.out.print("Digite uma opção: ");

            int opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    usuarios.add(Cadastrar());
                    break;

                case 2:
                    Listar(usuarios);
                    break;
                case 3:
                    MostrarDetalhesUsuario(usuarios);
                    break;
                case 4:
                    DeletarUsuario(usuarios);
                    break;

                default:
                    System.out.print("\nOpção Inválida!");
                    break;

                case 5:
                    System.out.print("\nAté logo!");
                    showcase = false;
                    break;
            }
        }
    }



    public static Usuario Cadastrar() {
        var usuario = new Usuario();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Vamos começar seu cadastro!");
        System.out.println("Digite seu primeiro nome: ");
        usuario.setNome(scanner.nextLine());
        System.out.println("Digite seu sobrenome: ");
        usuario.setSobrenome(scanner.nextLine());

        System.out.println("Digite seu email: ");
        usuario.setEmail(scanner.nextLine());
        while(usuario.getEmail()==""){
            System.out.println("Email inválido! Digite o email novamente: ");
            usuario.setEmail(scanner.nextLine());
        }

        System.out.println("Digite sua data de nascimento (digite nesse formato 'dd/mm/aaaa'): ");
        usuario.setNascimento(scanner.nextLine());
        while(usuario.getNascimento()==null){
            System.out.println("Data de nascimento inválida! Digite a data novamente: ");
            usuario.setNascimento(scanner.nextLine());
        }

        System.out.println("Digite seu gênero: ");
        usuario.setGenero(scanner.nextLine());

        System.out.println("Digite seu telefone(use apenas numéros): ");
        usuario.setTelefone(scanner.nextLine());
        while(usuario.getTelefone()==null){
            System.out.println("Número de telefone inválido! Digite o telefone novamente: ");
            usuario.setTelefone(scanner.nextLine());
        }
        System.out.println("Digite o cep: ");
        usuario.setCep(scanner.nextLine());
        while (usuario.getCep() == null){
            System.out.println("CEP inválido! Digite o CEP novamente: ");
            usuario.setCep(scanner.nextLine());
        }


        System.out.println("Digite o nome da rua do seu endereço: ");
        usuario.setRua(scanner.nextLine());
        System.out.println("Digite o número da sua residência: ");
        usuario.setComplemento(scanner.nextLine());
        System.out.println("Digite o complemento (caso não tenha, digite nulo): ");
        usuario.setComplemento(scanner.nextLine());
        System.out.println("Digite a cidade: ");
        usuario.setCidade(scanner.nextLine());
        return usuario;
    }

    public static void Listar(ArrayList<Usuario> lista){
        if (lista.size()==0){
            System.out.println("Não existem usuários cadastrados!");
        }
        else {
            for (int i = 0; i < lista.size(); i++) {
                System.out.println(i + 1 + " - " + lista.get(i).getNome()+ " " + lista.get(i).getSobrenome());
            }
        }

    }

    public static void MostrarDetalhesUsuario(ArrayList<Usuario> lista){
        Scanner scanner = new Scanner(System.in);
        if (lista.size()==0){
            System.out.println("Não existem usuários cadastrados!");
        }
        else {
            for (int i = 0; i < lista.size(); i++) {
                System.out.println(i + 1 + " - " + lista.get(i).getNome() + " " + lista.get(i).getSobrenome());
            }
            System.out.println("Digite a posição do usuário que deseja ver todos os dados: ");
            var usuario = scanner.nextInt();
            System.out.println(lista.get(usuario-1));
        }
    }

    public static void DeletarUsuario(ArrayList<Usuario> lista){
        Scanner scanner = new Scanner(System.in);
        if (lista.size()==0){
            System.out.println("Não existem usuários cadastrados!");
        }
        else {
            for (int i = 0; i < lista.size(); i++) {
                System.out.println(i + 1 + " - " + lista.get(i).getNome() + " " + lista.get(i).getSobrenome());
            }
            System.out.println("Digite a posição do usuário que deseja remover: ");
            var remover = scanner.nextInt();
            for (int i = 0; i < lista.size(); i++) {
                if (i == remover - 1) {
                    lista.remove(i);
                    System.out.println("Usuário removido com sucesso!");
                }
            }
        }
    }
}

