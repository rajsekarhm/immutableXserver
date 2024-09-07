package com.persistence.orm;

public interface IOrmActions<T,X> {
    public  void create(Class<T>entity) throws  Exception;
    public  void update(Class<T> entity)throws  Exception;
    public  T getBy(T entity)throws  Exception;
    public  void deleteBy(T entity)throws  Exception;
    public X connection()throws  Exception;
}
