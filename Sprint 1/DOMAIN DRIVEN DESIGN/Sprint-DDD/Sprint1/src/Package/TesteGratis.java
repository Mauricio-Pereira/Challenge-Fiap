package Package;

public class TesteGratis extends Cliente{
    private Cliente cliente;

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
