package com.persistence.query;
import java.lang.reflect.Field;

public class QueryBuilder<T>  {
    public  StringBuilder createQueryBuilder(Class<T> type,String tableName){
        StringBuilder query = new StringBuilder("INSERT INTO ");
        query.append(tableName).append("(");
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

    public StringBuilder updateQueryBuilder(Class<T> type, String tableName, String updateBy, String updateValue,String... updateKeys){
        StringBuilder query = new StringBuilder("UPDATE ");
        query.append(tableName.concat(" ")).append("SET ");
        for (String property : updateKeys){
            query.append(property.concat(" = ?,"));
        }
        query = removeSuffixString(query);
        query.append(" WHERE ").append(updateBy.concat("=".concat(updateValue)));
        return  query;
    }

    public String getByQueryBuilder(Class<T> type,String getBy){
        return  "";
    }

    public String deleteQueryBuilder( Class<T> type){
        return  "";
    }

    public  String getClassName(Class<?> type){
        return  type.getSimpleName().toLowerCase();
    }

    public  StringBuilder removeSuffixString(StringBuilder query) {
        return query.deleteCharAt(query.length()-1);
    }
}
