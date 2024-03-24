package org.fiap.repositories;

import org.fiap.entities._BaseEntity;

import java.util.List;

public interface _BaseRepository<T extends _BaseEntity> {
    public void Create(T entity);
    public List<T> ReadAll();
    public boolean DeleteById(int id);
    public boolean Update(T entity);
    public T ReadById(int id);
}
