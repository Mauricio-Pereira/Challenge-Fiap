package PackagePrincipal;

import java.util.Scanner;

public class Login {
    private String usuario;
    private String senha;


    public void FazerLogin(){
        var scanner = new Scanner(System.in);
        System.out.println("Usuário: ");
        setUsuario(scanner.nextLine());

        System.out.println("Senha: ");
        setSenha(scanner.nextLine());

        System.out.println("Logado com Sucesso");

    }
    public void FazerLogout(){
        System.out.println("Deslogado com sucesso");
    }

    public Login(){}
    public Login(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Login{" +
                "usuario='" + usuario + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
