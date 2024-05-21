package org.fiap.services;

import org.fiap.entities.Cliente;
import org.fiap.entities.Produto;
import org.fiap.repositories.ClienteRepository;
import org.fiap.repositories.ProdutoRepository;

public class ProdutoService {
    ProdutoRepository produtoRepository = new ProdutoRepository();
    ClienteRepository clienteRepository = new ClienteRepository();

    public ProdutoService(){
        ProdutoRepository produtoRepository = new ProdutoRepository();
    }

    public boolean Create(Produto produto, int clienteId) {
        var validation = produto.validate();
        try {
            if (validation.containsKey(false)) {
                throw new IllegalArgumentException(validation.get(false).toString());
            } else {
                Cliente cliente = clienteRepository.ReadById(clienteId);
                if (cliente == null) {
                    throw new IllegalArgumentException("Cliente não encontrado");
                }
                produto.setCliente(cliente);
                produtoRepository.Create(produto, clienteId);
                return true;
            }
        } catch (Exception e) {
            throw e;
        }
    }


    public void UpdateById(Produto produto, int id){
        var validation = produto.validate();
        try {
            if(validation.containsKey(false)) {
                throw new IllegalArgumentException(validation.get(false).toString());
            } else {
                produtoRepository.UpdateById(produto, id);
            }
        } catch (Exception e) {
            throw e;
        }

    }
}
