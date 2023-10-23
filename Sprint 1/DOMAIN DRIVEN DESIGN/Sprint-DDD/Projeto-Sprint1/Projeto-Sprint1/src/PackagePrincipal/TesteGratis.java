package PackagePrincipal;

public class TesteGratis extends Cadastro{
    private Cadastro cliente;

    public void ValidaInformacoes(){}
    public void AtivarTesteGratis(){}

    public TesteGratis(){}
    public TesteGratis(Cadastro cliente) {
        this.cliente = cliente;
    }

    public Cadastro getCliente() {
        return cliente;
    }

    public void setCliente(Cadastro cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "TesteGratis{" +
                "cliente=" + cliente +
                '}';
    }
}
