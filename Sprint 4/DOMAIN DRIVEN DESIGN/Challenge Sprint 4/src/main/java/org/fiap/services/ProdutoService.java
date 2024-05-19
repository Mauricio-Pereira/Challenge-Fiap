package org.fiap.services;

import org.fiap.entities.Produto;
import org.fiap.repositories.ProdutoRepository;

public class ProdutoService {
    ProdutoRepository produtoRepository = new ProdutoRepository();

    public ProdutoService(){
        ProdutoRepository produtoRepository = new ProdutoRepository();
    }

    public boolean Create(Produto produto, int id){
        var validation = produto.validate();
        try {
            if(validation.containsKey(false)) {
                throw new IllegalArgumentException(validation.get(false).toString());
            } else {
                produtoRepository.Create(produto, id);
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
