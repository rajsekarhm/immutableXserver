package com.persistence.query;
import java.lang.reflect.Field;


import java.lang.reflect.Field;

public class QueryBuilder<T>  {
    public  StringBuilder createQueryBuilder(Class<T> type){
        // INSERT INTO metadata(assetType,assetId,walletAddress,isApproved,assetInUsd) VALUES (?,?,?,?,?)
        StringBuilder query = new StringBuilder("INSERT INTO ");
        query.append(getClassName(type)).append("(");
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

    public StringBuilder updateQueryBuilder(Class<T> type, String updateBy,String... updateKeys){
        //  UPDATE metadata SET assetType = ?, walletAddress = ?, isApproved = ?, assetInUsd = ? WHERE assetId = ?;
        //UPDATE metadata SET assetId = ?,a = ?,b = ?,c = ?,d = ? WHERE MetaData= ?
        StringBuilder query = new StringBuilder("UPDATE ");
        query.append(getClassName(type).concat(" ")).append("SET ");
        for (String property : updateKeys){
            query.append(property.concat(" = ?, "));
        }
        query = removeSuffixString(removeSuffixString(query));
        query.append(" WHERE ").append(updateBy.concat("=".concat(" ? ")));
        return  query;
    }

    public StringBuilder getByQueryBuilder(Class<T> type,String getBy,String... args){
        // SELECT x, y, z FROM metadata WHERE assetId = ?
        //  SELECT assetType, assetId, walletAddress, isApproved, assetInUsd FROM metadata WHERE assetId = ?;
        StringBuilder query = new StringBuilder("SELECT ");
        for(String key: args){
            query.append(key.concat(", "));
        }
        query = removeSuffixString(removeSuffixString((query)));
        query.append(" FROM ");
        query.append(getClassName(type));
        query.append(" WHERE ");
        query.append(getBy.concat(" = ?"));
        return  query;
    }

    public StringBuilder deleteQueryBuilder( Class<T> type,String deleteBy){
        // DELETE FROM metadata WHERE assetId = ?;
        StringBuilder query = new StringBuilder("DELETE ");
        query.append("FROM ");
        query.append(getClassName(type).concat(" "));
        query.append("WHERE ");
        query.append(deleteBy.concat(" = ?"));
        return query;
    }

    public  String getClassName(Class<?> type){
        return  type.getSimpleName().toLowerCase();
    }

    public  StringBuilder removeSuffixString(StringBuilder query) {
        return query.deleteCharAt(query.length()-1);
    }
}
