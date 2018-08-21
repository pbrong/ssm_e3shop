package com.iteason.jedis;

import static org.junit.Assert.*;

import org.junit.Test;

import redis.clients.jedis.Jedis;

public class JedisTest {

	@Test
	public void testJedis() throws Exception {
		//创建一个jedis对象并连接
		Jedis jedis = new Jedis("47.106.133.57",6379);
		//使用jedis操作redis
		//写入数据
		jedis.set("hello","jedis");
		//读取出数据
		String string = jedis.get("hello");
		System.out.println(string);
		//关流
		jedis.close();
	}
}
