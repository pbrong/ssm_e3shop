/*package com.iteason.jedis;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

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
	
	@Test
	public void testJedisPool() throws Exception {
		//创建一个jedis连接池
		JedisPool pool = new JedisPool("47.106.133.57",6379);
		//通过连接池获取jedis
		Jedis jedis = pool.getResource();
		//通过jedis操作redis
		jedis.set("redispool","helloredispool");
		System.out.println(jedis.get("redispool"));
		//一定要关闭资源
		jedis.close();
		pool.close();
	}
	
	@Test
	public void testJedisClient() throws Exception {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext-redis.xml");
		JedisClient jedisClient = applicationContext.getBean(JedisClient.class);
		jedisClient.set("jedisClient", "helloworld");
		System.out.println(jedisClient.get("jedisClient"));
		System.out.println("测试成功！");
	}
}
*/