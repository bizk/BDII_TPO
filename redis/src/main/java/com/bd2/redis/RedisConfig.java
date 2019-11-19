package com.bd2.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.Topic;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

@Configuration
public class RedisConfig {
	private String url = "redis-14398.c84.us-east-1-2.ec2.cloud.redislabs.com";
	private int port = 14398;
	
	//This is the connection factory
	/*@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(url, port);
		redisStandaloneConfiguration.setPassword("Abcde12345!");
		return new JedisConnectionFactory(redisStandaloneConfiguration);
	}
	
	//This file will be used to query data from a repository
	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate template = new RedisTemplate();
		template.setConnectionFactory(jedisConnectionFactory());
		return template;
	}*/
}
