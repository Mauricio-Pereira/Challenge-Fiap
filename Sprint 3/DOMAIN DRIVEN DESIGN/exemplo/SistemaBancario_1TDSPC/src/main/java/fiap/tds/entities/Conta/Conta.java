package fiap.tds.entities.Conta;

public abstract class Conta {
    public int id;
    public String numero;
    public double saldo;

    public Conta() {
    }

    public Conta(int id, String numero, double saldo) {
        this.id = id;
        this.numero = numero;
        this.saldo = saldo;
    }

    public Conta(String numero, double saldo) {
        this.numero = numero;
        this.saldo = saldo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "Conta{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", saldo=" + saldo +
                '}';
    }
}
