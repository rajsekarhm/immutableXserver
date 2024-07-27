package com.immutable.request.entities;

public interface IEntitiesHandlers<T> {

    public T createUser();
    public T getUser();
    public T updateUser();
    public T deleteUser();
}
