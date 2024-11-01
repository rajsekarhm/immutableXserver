package com.dependencies.jedis;
import redis.clients.jedis.Jedis;
import java.util.List;
import java.util.Map;

 public class JedisImx implements IJedis {
    static  Jedis jediss;
    public JedisImx(){
        makeConnection();
    }
//    public  JedisImx(String token){
//        this.jediss =  new Jedis("exciting-rattler-44268.upstash.io", 6379, true);
//        this.jediss.auth(token);
//    }
    public static Jedis makeConnection(){
        if(jediss == null){
            jediss = new Jedis("127.0.0.1", 6379);
            System.out.println("Connection to server successfully");
            System.out.println("Server is running: "+jediss.ping());
        }
        return jediss;
    }

    @Override
    public byte[] getByByte(byte[] param) {
        return this.jediss.get(param);
    }

    @Override
    public byte[] getRangeByByte(byte[] param, long from, long to) {
        return this.jediss.getrange(param,from,to);
    }

    @Override
    public String getByString(String param) {
        return this.jediss.get(param);
    }

    @Override
    public String getRangeByString(String param, long from, long to) {
        return this.jediss.getrange(param,from,to);
    }

    @Override
    public  String setByString(String key, String vale){
        return  this.jediss.set(key,vale);
    }


     @Override
     public long setRangeByString(String key, long offset, String value) {
         return this.jediss.setrange(key,offset,value);
     }

     @Override
     public Map<String, String> hSetAll(String key) {
         return Map.of();
     }

     @Override
    public List<String> mGet(String... key) {
        return this.jediss.mget(key);
    }

    @Override
    public Map<String, String> hGetAll(String key) {
        return this.jediss.hgetAll(key);
    }

    @Override
    public String hGetByString(String key,String field) {
        return this.jediss.hget(key,field);
    }

    @Override
    public String setByByte(byte[] key, byte[] value) {
        return this.jediss.set(key,value);
    }

     @Override
    public long setRangeByByte(byte[] key, long offset, byte[] value) {
        return this.jediss.setrange(key,offset,value);
    }

    @Override
    public String mSet(String... key) {
        return this.jediss.mset(key);
    }

    @Override
    public long hSetByString(String key, Map<String, String> field) {
        return this.jediss.hset(key,field);
    }
}
