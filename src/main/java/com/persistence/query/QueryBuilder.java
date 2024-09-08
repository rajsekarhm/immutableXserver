package com.persistence.query;
import java.lang.reflect.Field;

public class QueryBuilder<T>  {
    public  StringBuilder createQueryBuilder(Class<T> type){
        StringBuilder query = new StringBuilder("INSERT INTO ");
        query.append(type.getSimpleName().toLowerCase()).append("(");
        Field[] fields = type.getDeclaredFields();
        for(Field _filed:fields){
            query.append(_filed.getName()).append(",");
        }
        query.deleteCharAt(query.length()-1).append(") VALUES (");
        for(Field _filed:fields){
            query.append("?,");
        }
        query.deleteCharAt(query.length() - 1);
        query.append(")");
        return  query;
    }

    public String getQueryBuilder(Class<T> type){
        StringBuilder query = new StringBuilder("UPDATE");
        query.append(type.getSimpleName().toLowerCase()).append("SET");

        return  "";
    }

    public String getByQueryBuilder(Class<T> type){
        return  "";
    }

    public String deleteQueryBuilder( Class<T> type){
        return  "";
    }
}
