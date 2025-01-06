package com.dependencies.jedis;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;
import java.util.Map;

@Component
 public class JedisImx implements IJedis {
    static  Jedis jediss;
    static  JedisPool jedisPool;
    public JedisImx(){
        getConnection();
    }
     static {
         JedisPoolConfig poolConfig = new JedisPoolConfig();
         poolConfig.setMaxTotal(500);
         poolConfig.setMaxIdle(10);
         poolConfig.setMinIdle(2);
         poolConfig.setTestOnBorrow(true);
         poolConfig.setTestOnReturn(true);
         jedisPool = new JedisPool(poolConfig, "127.0.0.1", 6379); // Adjust host and port as needed
     }
    public static Jedis getConnection(){
        return jedisPool.getResource();
    }

    @Override
    public byte[] getByByte(byte[] param) {
        try(Jedis jediss = JedisImx.getConnection()){
            return jediss.get(param);
        }
    }

    @Override
    public byte[] getRangeByByte(byte[] param, long from, long to) {
        try(Jedis jediss = JedisImx.getConnection()){}
        return jediss.getrange(param,from,to);
    }

    @Override
    public String getByString(String param) {
        try(Jedis jediss = JedisImx.getConnection()){
            return jediss.get(param);
        }
    }

    @Override
    public String getRangeByString(String param, long from, long to) {
        return jediss.getrange(param,from,to);
    }

    @Override
    public  String setByString(String key, String vale){
        try(Jedis jediss = JedisImx.getConnection()){
            return  jediss.set(key,vale);
        }

    }


     @Override
     public long setRangeByString(String key, long offset, String value) {
         return jediss.setrange(key,offset,value);
     }

     @Override
     public Map<String, String> hSetAll(String key) {
         return Map.of();
     }

     @Override
    public List<String> mGet(String... key) {
        return jediss.mget(key);
    }

    @Override
    public Map<String, String> hGetAll(String key) {
        return jediss.hgetAll(key);
    }

    @Override
    public String hGetByString(String key,String field) {
        return jediss.hget(key,field);
    }

    @Override
    public String setByByte(byte[] key, byte[] value) {
        return jediss.set(key,value);
    }

     @Override
    public long setRangeByByte(byte[] key, long offset, byte[] value) {
        return jediss.setrange(key,offset,value);
    }

    @Override
    public String mSet(String... key) {
        return jediss.mset(key);
    }

    @Override
    public long hSetByString(String key, Map<String, String> field) {
        return jediss.hset(key,field);
    }

     @Override
     public Boolean exists(String id) {
         return jediss.exists(id);
     }
}
