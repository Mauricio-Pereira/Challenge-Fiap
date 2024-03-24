package org.fiap.repositories;

import org.fiap.entities._BaseEntity;

import java.util.List;

public class EnderecoRepository <T extends _BaseEntity> implements _BaseRepository<T>{
    @Override
    public void Create(T entity) {

    }

    @Override
    public List<T> ReadAll() {
        return null;
    }

    @Override
    public boolean DeleteById(int id) {
        return false;
    }

    @Override
    public boolean UpdateById(T entity, int id) {
        return false;
    }

    @Override
    public T ReadById(int id) {
        return null;
    }
}
