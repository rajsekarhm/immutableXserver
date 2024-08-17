package com.immutable.request.entities;

public interface IEntitiesHandlers<T> {

    public T createEntities();
    public T getEntities();
    public T updateEntities();
    public T deleteEntities();
}
