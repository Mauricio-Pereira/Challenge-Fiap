package org.fiap.services;

import org.fiap.entities.Cliente;
import org.fiap.repositories.ClienteRepository;

public class ClienteService {
    private ClienteRepository clienteRepository = new ClienteRepository();

    public ClienteService(){
        ClienteRepository clienteRepository = new ClienteRepository();
    }

    public boolean Create(Cliente cliente){
        var validation = cliente.validate();
        try {
            if(validation.containsKey(false)) {
                throw new IllegalArgumentException(validation.get(false).toString());
            } else {
                clienteRepository.Create(cliente);
                return true;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void UpdateById(Cliente cliente, int id){
        var validation = cliente.validate();
        try {
            if(validation.containsKey(false)) {
                throw new IllegalArgumentException(validation.get(false).toString());
            } else {
                clienteRepository.UpdateById(cliente, id);
            }
        } catch (Exception e) {
            throw e;
        }

    }
}
