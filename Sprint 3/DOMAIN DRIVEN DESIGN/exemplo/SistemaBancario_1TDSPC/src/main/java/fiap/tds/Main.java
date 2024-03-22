package fiap.tds;

import fiap.tds.entities.Conta.ContaCorrente;
import fiap.tds.entities.Conta.ContaPoupanca;
import fiap.tds.repositories.ContaRepository;

public class Main {
    public static void main(String[] args) {
        var novaConta = new ContaCorrente("123", 1000, true);
        var outraConta = new ContaPoupanca("456", 2000, 13);
        var contaRepo = new ContaRepository();
        contaRepo.create(novaConta);
        contaRepo.create(outraConta);
        System.out.println(contaRepo.readAll());
        //contaRepo.shutdown();
    }
}