package com.persistence.orm;

public interface IOrmActions<T,X> {
    public  void create(T entity) throws  Exception;
    public  void update(T entity,String tableName,String updateBy, String updateValue,String... updateKeys)throws  Exception;
    public  T getBy(T entity)throws  Exception;
    public  void deleteBy(T entity)throws  Exception;
    public X connection()throws  Exception;
}
