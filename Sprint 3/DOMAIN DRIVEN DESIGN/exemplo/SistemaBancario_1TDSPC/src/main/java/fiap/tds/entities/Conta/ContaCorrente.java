package fiap.tds.entities.Conta;

public class ContaCorrente extends Conta{
    private boolean ChequeEspecialHabilitado;

    public ContaCorrente() {
    }

    public ContaCorrente(int id, String numero, double saldo, boolean chequeEspecialHabilitado) {
        super(id, numero, saldo);
        ChequeEspecialHabilitado = chequeEspecialHabilitado;
    }

    public ContaCorrente(String numero, double saldo, boolean chequeEspecialHabilitado) {
        super(numero, saldo);
        ChequeEspecialHabilitado = chequeEspecialHabilitado;
    }

    public boolean isChequeEspecialHabilitado() {
        return ChequeEspecialHabilitado;
    }

    public void setChequeEspecialHabilitado(boolean chequeEspecialHabilitado) {
        ChequeEspecialHabilitado = chequeEspecialHabilitado;
    }

    @Override
    public String toString() {
        return "ContaCorrente{" +
                "ChequeEspecialHabilitado=" + ChequeEspecialHabilitado +
                ", id=" + id +
                ", numero='" + numero + '\'' +
                ", saldo=" + saldo +
                "} " + super.toString();
    }
}
