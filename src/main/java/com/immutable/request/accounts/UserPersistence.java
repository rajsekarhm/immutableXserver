package com.immutable.request.accounts;

import com.persistence.database.SqlHandler;
import com.persistence.orm.IOrmActions;
import com.persistence.query.QueryBuilder;

import java.sql.Connection;

public class UserPersistence implements IOrmActions<UserDAO,Connection> {
    private  String host;
    private  String username;
    private  String password;
    private  String database;
    public UserPersistence(String _host, String _username, String _password, String _database){
        this.database = _database;
        this.host = _host;
        this.username = _username;
        this.password = _password;

    }

    public  static  void  main(String[] args) throws Exception {
        Class<UserDAO> _class =  UserDAO.class;
        System.out.println(_class.getSimpleName());
        UserPersistence orm =  new UserPersistence("","","","");
        orm.create(UserDAO.class);
    }

    @Override
    public void create(Class<UserDAO> entity) throws  Exception {
        QueryBuilder<UserDAO>  _query =  new QueryBuilder<UserDAO>();
        StringBuilder query = _query.createQueryBuilder(UserDAO.class,_query.getClassName(UserDAO.class));
        System.out.println(query.toString());
        System.out.println(_query.updateQueryBuilder(UserDAO.class,_query.getClassName(UserDAO.class),"id","a","b","c").toString());
//        Connection con= connection();
//        con.setAutoCommit(false);
//        query.append(entity.getSimpleName().toLowerCase()).append("(");
//        Field[] fields = entity.getDeclaredFields();
//        for(Field _filed:fields){
//            query.append(_filed.getName()).append(",");
//        }
//        query.deleteCharAt(query.length()-1).append(") VALUES (");
//        for(Field _filed:fields){
//            query.append("?,");
//        }
//        query.deleteCharAt(query.length() - `1);
//        query.append(")");
//        System.out.println(query.toString());
//        PreparedStatement statement = con.prepareStatement(query.toString());
//        try{
//            for (int i = 0; i < fields.length; i++) {
//                fields[i].setAccessible(true);
//                statement.setObject(i+1,fields[i]);
//            }
//            statement.execute();
//            con.commit();
//        }catch (SQLException error){
//            if(con != null){
//                try {
//                    con.rollback();
//                }catch (SQLException err){
//                    err.printStackTrace();
//                }
//            }
//            error.printStackTrace();
//        }
//        finally {
//            if(statement != null) statement.close();
//            if(con != null) con.close();
//        }
    }

    @Override
    public void update(Class<UserDAO> entity) throws  Exception {

    }

    @Override
    public UserDAO getBy(UserDAO entity) throws  Exception {
        return null;
    }

    @Override
    public void deleteBy(UserDAO entity) throws  Exception {

    }

    @Override
    public Connection connection()throws  Exception {
        SqlHandler dbHandler = new SqlHandler();
        dbHandler.setDBUtilsProperty(this.host,this.username,this.password,this.database);
        return  dbHandler.getConnection();
    }
}
