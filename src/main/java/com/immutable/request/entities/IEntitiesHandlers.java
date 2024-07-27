package com.immutable.request.entities;

public interface IEntitiesHandlers<T> {

    public T createUser(T createUserObj);
    public T getUser(T getUserObj);
    public T updateUser(T updateUserObj);
    public T deleteUser(T deleteUserObj);
}
