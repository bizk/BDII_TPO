package com.bd2.redis.repositories;

import java.util.Map;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.bd2.redis.models.Building;

@Repository
public class BuildingRepository {
	public static String KEY;
	private RedisTemplate<String, Building> redisTemplate;
	private HashOperations hashOperations;
	
	public BuildingRepository(RedisTemplate<String, Building> redisTemplate) {
		this.redisTemplate = redisTemplate;
		hashOperations = redisTemplate.opsForHash();
	}
	
	public Map<Integer,Building> getAllItems() {
		System.out.println("entro aca!");
		return hashOperations.entries("SOG");
	}
}
