package fiap.tds.entities.Cliente;

import fiap.tds.entities.Conta.Conta;

import java.util.ArrayList;
import java.util.List;

public abstract class Cliente {
    public int id;
    public List<Conta> contas = new ArrayList<>();
}
