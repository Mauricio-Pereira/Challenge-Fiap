package org.fiap.utils;


import org.fiap.entities._BaseEntity;

public interface Logger<T extends _BaseEntity> {
    void logCreate(T entity);
    void logReadById(T entity);
    void logReadAll(T entity);
    void logUpdateById(T entity);
    void logDeleteById(T entity);

}
