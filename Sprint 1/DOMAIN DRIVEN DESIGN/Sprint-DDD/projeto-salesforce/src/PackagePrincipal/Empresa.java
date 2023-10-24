package PackagePrincipal;

public class Empresa extends Cliente {
    private String cliente;
    private String nomeEmpresa;
    private String setor;
    private String contato;
    private String endereco;


    public Empresa(){}
    public Empresa(String cliente, String nomeEmpresa, String setor, String contato, String endereco) {
        this.cliente = cliente;
        this.nomeEmpresa = nomeEmpresa;
        this.setor = setor;
        this.contato = contato;
        this.endereco = endereco;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
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
        return "Empresa:" +
                "\r\nNome do cliente: " + cliente +
                "\r\nNome da Empresa: " + nomeEmpresa +
                "\r\nSetor: " + setor +
                "\r\nContato: " + contato +
                "\r\nEndereço: " + endereco;
    }
}
