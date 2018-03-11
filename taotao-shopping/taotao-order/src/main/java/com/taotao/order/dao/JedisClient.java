package com.taotao.order.dao;

public interface JedisClient {
	String get(String key);
	String set(String key, String value);
	String hget(String hkey, String key);
	long hset(String hkey, String key, String value);
	long incr(String key);//自增
	long expire(String key, int second);//设置key的过期时间
	long ttl(String key);//显示key设置的过期时间
	long del(String key);
	long hdel(String hkey, String key);

}
