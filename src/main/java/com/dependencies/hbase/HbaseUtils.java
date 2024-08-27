package com.dependencies.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.util.Bytes;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HbaseUtils implements IHbaseUtils {
    public  static Connection connection;
    public  static String TABLE;
    public HbaseUtils(String table) throws IOException {
        TABLE = table;
        Logger.getLogger("org.apache.zookeeper").setLevel(Level.ALL);
        Logger.getLogger("org.apache.hadoop.hbase.zookeeper").setLevel(Level.ALL);
        Logger.getLogger("org.apache.hadoop.hbase.client").setLevel(Level.ALL);
        makeConnection();
    }

    @Override
    public void put(String rowKey, String value, String columnFamily, String column) throws Exception {
        Put put = new Put(Bytes.toBytes(rowKey));
        put.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(column), value.getBytes());
        Table table = getTable(TABLE);
        if(table!=null){
            System.out.println("Going to put in table");
            table.put(put);
            table.close();
            System.out.println("Done put");
        }
    }

    @Override
    public byte[] get(String rowKey,String columnFamily, String column) throws  Exception{
        Get get = new Get(Bytes.toBytes(rowKey));
        Table table = getTable(TABLE);
        try{
            if(table!=null){
                Result result =  table.get(get);
                return result.getValue(Bytes.toBytes(columnFamily),Bytes.toBytes(column));
            }else {
                return  new byte[0];
            }
        }catch (Error error){}
        return new byte[0];
    }

    @Override
    public void scan(String columnFamily,String column) throws  Exception {
        Scan scan = new Scan();
        scan.addColumn(Bytes.toBytes(columnFamily),Bytes.toBytes(column));
        Table table = getTable(TABLE);
        ResultScanner scanner = table.getScanner(scan);
        for (Result result = scanner.next(); result != null; result = scanner.next())
            System.out.println("Found row : " + result);
        scanner.close();
    }

    @Override
    public void delete(String rowKey,String columnFamily,String column) throws  Exception{
        Delete delete = new Delete(Bytes.toBytes(rowKey));
        delete.addColumn(Bytes.toBytes(columnFamily),Bytes.toBytes(column));
        Table table = getTable(TABLE);
        table.delete(delete);
        table.close();
    }

    public  void makeConnection() throws IOException {
        Configuration configuration =  HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.property.clientPort", "2189");
        configuration.set("hbase.zookeeper.quorum", "127.0.0.1");
        connection =  ConnectionFactory.createConnection(configuration);
    }

    public static Table getTable(String table) throws Exception{
        if(connection!=null){
            return connection.getTable(TableName.valueOf(table));
        }
        return null;
    }
}
