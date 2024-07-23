package com.configuration.jedis;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public interface IJedis {
    public byte[] getByByte(byte[] param) ;
    public byte[] getRangeByByte(byte[] param, long from, long to) ;
    public String getByString(String param) ;
    public String getRangeByString(String param, long from, long to) ;
    public List<String> mGet(String key);
    public Map<String, String> hGetAll(String key);
    public String hGetByString(String key,String field) ;
}
