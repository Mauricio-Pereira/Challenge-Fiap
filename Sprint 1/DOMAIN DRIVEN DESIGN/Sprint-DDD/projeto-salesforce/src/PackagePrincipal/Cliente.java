package PackagePrincipal;

import java.util.Scanner;

public class Cliente {
    private String nome;
    private int dataNascimento;
    private String genero;
    private String telefone;
    private String email;
    private String endereco;
    private boolean status = false;

    public boolean PossuiCadastro(){return true;}
    public void NovoCadastro(Cliente cliente){

        Scanner scanner = new Scanner(System.in);

        System.out.print("Nome: ");
        cliente.nome = (scanner.nextLine());

        System.out.print("Data de Nascimento (no formato YYYYMMDD): ");
        cliente.dataNascimento = (scanner.nextInt());

        System.out.println("Genero: ");
        cliente.genero = (scanner.nextLine());

        System.out.print("Telefone: ");
        cliente.telefone = (scanner.nextLine());

        System.out.print("E-mail: ");
        cliente.email = (scanner.nextLine());

        System.out.print("Endereço: ");
        cliente.endereco = (scanner.nextLine());
    }


    public Cliente(){}
    public Cliente(String nome, int dataNascimento, String genero, String telefone, String email, String endereco, boolean Status) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.status = Status;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(int dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean Status) {
        this.status = Status;
    }

    @Override
    public String toString() {
        return "Clientes: " +
                "\r\n\"Nome: " + nome +
                "\r\nData Nascimento: " + dataNascimento +
                "\r\nGenero: " + genero + '\'' +
                "\r\nTelefone: " + telefone + '\'' +
                "\r\nEmail: " + email + '\'' +
                "\r\nEndereco: " + endereco + '\'' +
                "\r\nStatus: " + status
                ;
    }
}
