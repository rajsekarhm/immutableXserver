package com.immutable.request.controller;

public interface AccountFactory<T> {
    public  T create(T createEntity);
}
