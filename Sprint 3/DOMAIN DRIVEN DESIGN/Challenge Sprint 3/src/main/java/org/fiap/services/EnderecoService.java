package org.fiap.services;

import org.fiap.entities.Endereco;
import org.fiap.repositories.EnderecoRepository;

public class EnderecoService {
    private EnderecoRepository enderecoRepository = new EnderecoRepository();

    public EnderecoService(){
        EnderecoRepository enderecoRepository = new EnderecoRepository();
    }

    public boolean Create(Endereco endereco){
        var validation = endereco.validate();
        try {
            if(validation.containsKey(false)) {
                throw new IllegalArgumentException(validation.get(false).toString());
            } else {
                enderecoRepository.Create(endereco);
                return true;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void UpdateById(Endereco endereco, int id){
        var validation = endereco.validate();
        try {
            if(validation.containsKey(false)) {
                throw new IllegalArgumentException(validation.get(false).toString());
            } else {
                enderecoRepository.UpdateById(endereco, id);
            }
        } catch (Exception e) {
            throw e;
        }

    }

}
