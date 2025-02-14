package com.immutable.request.accounts.user;

import com.persistence.orm.IOrmActions;

import java.sql.Connection;

public class UserPersistence implements IOrmActions<User,Connection> {
    @Override
    public void create(User entity) throws Exception {

    }

    @Override
    public void update(User entity, String tableName, String updateBy, String updateValue, String... updateKeys) throws Exception {

    }

    @Override
    public User getBy(User entity) throws Exception {
        return null;
    }

    @Override
    public void deleteBy(User entity) throws Exception {

    }

    @Override
    public Connection connection() throws Exception {
        return null;
    }
//    private  String host;
//    private  String username;
//    private  String password;
//    private  String database;
//    public UserPersistence(String _host, String _username, String _password, String _database){
//        this.database = _database;
//        this.host = _host;
//        this.username = _username;
//        this.password = _password;
//
//    }
//
//    public  static  void  main(String[] args) throws Exception {
////        Class<UserDAO> _class =  UserDAO.class;
////        System.out.println(_class.getSimpleName());
////        UserDAO dao = new UserDAO();
////        UserPersistence orm =  new UserPersistence("","","","");
//////        orm.create(dao);
////        orm.update(dao,"a","b","x","y","z");
//    }
//
//    @Override
//    public void create(UserDAO entity) throws  Exception {
//        Class<?> entityClass = entity.getClass();
//        QueryBuilder<UserDAO>  _query =  new QueryBuilder<UserDAO>();
//        StringBuilder query = _query.createQueryBuilder(UserDAO.class,_query.getClassName(entityClass.getClass()));
//        Connection con= connection();
//        con.setAutoCommit(false);
//        Field[] fields = entityClass.getDeclaredFields();
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
//    }
//
//    @Override
//    public void update(UserDAO entity,String tableName,String updateBy, String updateValue,String... updateKeys) throws  Exception {
//        QueryBuilder<UserDAO>  _query =  new QueryBuilder<UserDAO>();
//        StringBuilder query = _query.updateQueryBuilder(UserDAO.class,tableName,updateBy,updateValue,updateKeys);
//        System.out.println(query.toString());
//        Connection con = connection();
//        con.setAutoCommit(false);
//        PreparedStatement statement = con.prepareStatement(query.toString());
//        try{
//            for (int i = 0; i < updateKeys.length; i++) {
//                statement.setObject(i+1,getValueByStringField(entity,updateKeys[i]));
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
//    }
//
//    @Override
//    public UserDAO getBy(UserDAO entity) throws  Exception {
//        return null;
//    }
//
//    @Override
//    public void deleteBy(UserDAO entity) throws  Exception {
//
//    }
//
//    @Override
//    public Connection connection()throws  Exception {
//        SqlHandler dbHandler = new SqlHandler();
//        dbHandler.setDBUtilsProperty(this.host,this.username,this.password,this.database);
//        return  dbHandler.getConnection();
//    }
//
//    public Object getValueByStringField(Object _object,String fieldName) throws NoSuchFieldException, IllegalAccessException {
//        Class<?> clazz = _object.getClass();
//        Field field = clazz.getDeclaredField(fieldName);
//        field.setAccessible(true);
//        return field.get(_object);
//    }
}
