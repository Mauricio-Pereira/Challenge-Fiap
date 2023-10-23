package Package;

public class Empresa extends Cliente{
    private Cliente cliente;
    private String nomeEmpresa;
    private String setor;
    private String contato;
    private String endereco;

    public Empresa(Cliente cliente, String nomeEmpresa, String setor, String contato, String endereco) {
        this.cliente = cliente;
        this.nomeEmpresa = nomeEmpresa;
        this.setor = setor;
        this.contato = contato;
        this.endereco = endereco;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    @Override
    public String getEndereco() {
        return endereco;
    }

    @Override
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Empresa{" +
                "cliente=" + cliente +
                ", nomeEmpresa='" + nomeEmpresa + '\'' +
                ", setor='" + setor + '\'' +
                ", contato='" + contato + '\'' +
                ", endereco='" + endereco + '\'' +
                '}';
    }
}
