package com.dependencies.jedis;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import java.util.List;
import java.util.Map;

@Component
public class JedisImx implements IJedis {
    static  JedisPool jedisPool;

    public JedisImx(){
    }
     static {
         JedisPoolConfig poolConfig = new JedisPoolConfig();
         poolConfig.setMaxTotal(500);
         poolConfig.setMaxIdle(10);
         poolConfig.setMinIdle(2);
         poolConfig.setTestOnBorrow(true);
         poolConfig.setTestOnReturn(true);
         jedisPool = new JedisPool(poolConfig, "127.0.0.1", 6379);
     }
    public static Jedis getConnection(){
        return jedisPool.getResource();
    }

    @Override
    public byte[] getByByte(byte[] param) {
        try(Jedis jedis = JedisImx.getConnection()){
            return jedis.get(param);
        }
    }

    @Override
    public byte[] getRangeByByte(byte[] param, long from, long to) {
        try(Jedis jedis = JedisImx.getConnection()){
            return jedis.getrange(param, from, to);
        }
    }

    @Override
    public String getByString(String param) {
        try(Jedis jedis = JedisImx.getConnection()){
            return jedis.get(param);
        }
    }

    @Override
    public String getRangeByString(String param, long from, long to) {
        try(Jedis jedis = JedisImx.getConnection()){
            return jedis.getrange(param, from, to);
        }
    }

    @Override
    public  String setByString(String key, String value){
        try(Jedis jedis = JedisImx.getConnection()){
            return jedis.set(key, value);
        }
    }

     @Override
     public long setRangeByString(String key, long offset, String value) {
         try(Jedis jedis = JedisImx.getConnection()){
             return jedis.setrange(key, offset, value);
         }
     }

     @Override
     public Map<String, String> hSetAll(String key) {
         return Map.of();
     }

     @Override
    public List<String> mGet(String... key) {
        try(Jedis jedis = JedisImx.getConnection()){
            return jedis.mget(key);
        }
    }

    @Override
    public Map<String, String> hGetAll(String key) {
        try(Jedis jedis = JedisImx.getConnection()){
            return jedis.hgetAll(key);
        }
    }

    @Override
    public String hGetByString(String key, String field) {
        try(Jedis jedis = JedisImx.getConnection()){
            return jedis.hget(key, field);
        }
    }

    @Override
    public String setByByte(byte[] key, byte[] value) {
        try(Jedis jedis = JedisImx.getConnection()){
            return jedis.set(key, value);
        }
    }

     @Override
    public long setRangeByByte(byte[] key, long offset, byte[] value) {
        try(Jedis jedis = JedisImx.getConnection()){
            return jedis.setrange(key, offset, value);
        }
    }

    @Override
    public String mSet(String... key) {
        try(Jedis jedis = JedisImx.getConnection()){
            return jedis.mset(key);
        }
    }

    @Override
    public long hSetByString(String key, Map<String, String> field) {
        try(Jedis jedis = JedisImx.getConnection()){
            return jedis.hset(key, field);
        }
    }

     @Override
     public Boolean exists(String id) {
         try(Jedis jedis = JedisImx.getConnection()){
             return jedis.exists(id);
         }
     }

     @Override
     public long del(String key) {
         try(Jedis jedis = JedisImx.getConnection()){
             return jedis.del(key);
         }
     }
}
