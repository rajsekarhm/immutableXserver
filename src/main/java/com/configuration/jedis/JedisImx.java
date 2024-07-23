package com.configuration.jedis;
import redis.clients.jedis.Jedis;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class JedisImx implements IJedis {
    Jedis jediss;
    public  JedisImx(){
        this.jediss = new Jedis();
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
    public List<String> mGet(String key) {
        return Collections.singletonList(this.jediss.mset(key));
    }

    @Override
    public Map<String, String> hGetAll(String key) {
        return this.jediss.hgetAll(key);
    }

    @Override
    public String hGetByString(String key,String field) {
        return this.jediss.hget(key,field);
    }
}
