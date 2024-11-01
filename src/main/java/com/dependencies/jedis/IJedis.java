package com.dependencies.jedis;
import java.util.List;
import java.util.Map;

public interface IJedis {
    public byte[] getByByte(byte[] param) ;
    public byte[] getRangeByByte(byte[] param, long from, long to) ;
    public String getByString(String param) ;
    public String getRangeByString(String param, long from, long to) ;
    public List<String> mGet(String... key);
    public Map<String, String> hGetAll(String key);
    public String hGetByString(String key,String field) ;
    public String setByByte(byte[] key, byte[] value);
    public long setRangeByByte(byte[] key, long offset, byte[] value);
    public String setByString(String key, String value);
    public long setRangeByString(String key, long offset, String value);
    public Map<String, String> hSetAll(String key);
    public String mSet(String... key);
    public long hSetByString(String key, Map<String, String> field);
}
