package PackagePrincipal;

public class TesteGratis extends Cliente {
    private Cliente cliente;

    public void ValidaInformacoes(){}
    public void AtivarTesteGratis(){}

    public TesteGratis(){}
    public TesteGratis(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "TesteGratis{" +
                "cliente=" + cliente +
                '}';
    }
}
