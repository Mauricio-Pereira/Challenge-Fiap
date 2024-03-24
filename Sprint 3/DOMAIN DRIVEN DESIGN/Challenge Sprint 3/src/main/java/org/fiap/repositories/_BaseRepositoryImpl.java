package org.fiap.repositories;

import org.fiap.entities._BaseEntity;
import org.fiap.utils.Logger;

import java.util.List;

public class _BaseRepositoryImpl <T extends _BaseEntity> implements _BaseRepository<T>{

    protected Logger<T> logger;
    @Override
    public void Create(T entity) {
        logger.logCreate(entity);
    }

    @Override
    public List<T> ReadAll() {
        logger.logReadAll(null);
        return null;
    }

    @Override
    public boolean DeleteById(int id) {
        logger.logDeleteById(ReadAll().get(id));
        return false;

    }

    @Override
    public boolean UpdateById(T entity, int id) {
        logger.logUpdateById(entity);
        return false;
    }

    @Override
    public T ReadById(int id){
        logger.logReadById(ReadAll().get(id));
        return null;
    }
}
