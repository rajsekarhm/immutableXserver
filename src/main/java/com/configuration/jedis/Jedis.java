package com.configuration.jedis;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;

abstract class JedisImx implements IJedis {
    Jedis jediss;
    public  JedisImx(String token){
        this.jediss =  new Jedis("exciting-rattler-44268.upstash.io", 6379, true);
        this.jediss.auth(token);
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
