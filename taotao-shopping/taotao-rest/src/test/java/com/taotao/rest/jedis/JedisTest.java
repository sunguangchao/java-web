package com.taotao.rest.jedis;

import java.io.IOException;
import java.util.HashSet;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class JedisTest {
	/**
	 * 单机版连接
	 */
	@Test
	public void testJedisSingle() {
		//创建一个Jedis对象
		Jedis jedis = new Jedis("192.168.2.225", 6379);
		jedis.auth("abcde12345");//这里为我设置的密码
		jedis.set("key1", "jedis test");
		String str = jedis.get("key1");
		System.out.println(str);
		jedis.close();
		//调用Jedis对象的方法，方法名称
	}
	
	/**
	 * 使用连接池
	 */
	@Test
	public void testJedisPool() {
		JedisPool pool = new JedisPool("192.168.2.225", 6379);
		Jedis jedis = pool.getResource();
		jedis.auth("abcde12345");
		jedis.set("key1", "jedis test");
		String str = jedis.get("key1");
		System.out.println(str);
		jedis.close();
		pool.close();
	}
	
	/**
	 * 集群版测试
	 */
	@Test
	public void testJedisCluster() {
		HashSet<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.2.225", 7001));
		nodes.add(new HostAndPort("192.168.2.225", 7002));
		nodes.add(new HostAndPort("192.168.2.225", 7003));
		nodes.add(new HostAndPort("192.168.2.225", 7004));
		nodes.add(new HostAndPort("192.168.2.225", 7005));
		nodes.add(new HostAndPort("192.168.2.225", 7006));
		JedisCluster cluster = new JedisCluster(nodes);
		cluster.set("key2","1000");
		System.out.println(cluster.get("key2"));
		try {
			cluster.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Jedis单机版测试
	 */
	@Test
	public void testSpringJedisSingle() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		JedisPool pool = (JedisPool) context.getBean("redisClient");
		Jedis jedis = pool.getResource();
		jedis.auth("abcde12345");
		String str = jedis.get("key1");
		System.out.println(str);
		jedis.close();
		pool.close();
	}
	/**
	 * Jedis集群版测试
	 */
	@Test
	public void testSpringJedisCluster() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		JedisCluster jedisCluster = (JedisCluster) context.getBean("redisClient");
		String str = jedisCluster.get("key2");
		System.out.println(str);
		try {
			jedisCluster.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
