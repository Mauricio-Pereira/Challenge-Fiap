package fiap.tds.entities.Conta;

public class ContaPoupanca extends Conta{
    public double taxa;

    public ContaPoupanca() {
    }


    public ContaPoupanca(int id, String numero, double saldo, double taxa) {
        super(id, numero, saldo);
        this.taxa = taxa;
    }

    public ContaPoupanca(String numero, double saldo, double taxa) {
        super(numero, saldo);
        this.taxa = taxa;
    }

    public double getTaxa() {
        return taxa;
    }

    public void setTaxa(double taxa) {
        this.taxa = taxa;
    }

    @Override
    public String toString() {
        return "ContaPoupanca{" +
                "taxa=" + taxa +
                ", id=" + id +
                ", numero='" + numero + '\'' +
                ", saldo=" + saldo +
                "} " + super.toString();
    }
}
