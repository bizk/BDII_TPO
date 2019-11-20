package com.bd2.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.cdi.RedisRepositoryBean;

import com.bd2.redis.DAOS.BuildingDAO;
import com.bd2.redis.dbConnection.DbConnection;
import com.bd2.redis.models.Building;

import redis.clients.jedis.Jedis;

@SpringBootApplication
public class RedisApplication {
	private String HOSTNAME = "localhost";
	private int PORT = 6379;
	
	private static DbConnection con;
	
	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
		jedisConnectionFactory.setHostName(HOSTNAME);
		jedisConnectionFactory.setPort(PORT);
		return jedisConnectionFactory;
	}
	
	@Bean
	RedisTemplate<String, Building> redisTemplate() {
		RedisTemplate<String,Building> redisTemplate = new RedisTemplate<String, Building>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		return redisTemplate;
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(RedisApplication.class, args);
		
	} 

}
