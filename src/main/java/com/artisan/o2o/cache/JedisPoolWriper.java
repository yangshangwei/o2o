package com.artisan.o2o.cache;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 
 * 
 * @ClassName: JedisPoolWriper
 * 
 * @Description: 强指定redis的JedisPool接口构造函数，创建jedispool
 * 
 * @author: Mr.Yang
 * 
 * @date: 2018年8月10日 上午10:45:51
 */
public class JedisPoolWriper {

	private JedisPool jedisPool;

	/**
	 * 
	 * 
	 * @Title:JedisPoolWriper
	 * 
	 * @Description:构造函数
	 * 
	 * @param poolConfig
	 * @param host
	 * @param port
	 */
	public JedisPoolWriper(final JedisPoolConfig poolConfig, final String host,
			final int port) {
		try {
			jedisPool = new JedisPool(poolConfig, host, port);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JedisPool getJedisPool() {
		return jedisPool;
	}

	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

}
