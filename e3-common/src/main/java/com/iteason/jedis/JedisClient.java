package com.iteason.jedis;

public interface JedisClient {

	String set(String key, String value);//插入数据
	String get(String key);//读取数据
	Long del(String key);//删除string类型数据
	Boolean exists(String key);//判断存在
	Long expire(String key, int seconds);//设置过期时间
	Long ttl(String key);//查询过期时间
	Long incr(String key);//自增
	Long hset(String key, String field, String value);//插入hash类型数据
	String hget(String key, String field);//获得hash类型数据
	Long hdel(String key, String... field);//删除hash数据
}
