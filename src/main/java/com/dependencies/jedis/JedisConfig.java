package com.dependencies.jedis;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisConfig {
    public  static JedisPool jedisPool;
    public static void init(){
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(50); // max connection
        poolConfig.setMaxIdle(10); // max idle connection
        poolConfig.setMinIdle(2); // min idle connection
        poolConfig.setTestOnBorrow(true);
        jedisPool = new JedisPool(poolConfig, "localhost", 6379);
    }
    public static JedisPool getPool() {
        return jedisPool;
    }
}