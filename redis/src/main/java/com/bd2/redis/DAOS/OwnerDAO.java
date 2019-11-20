package com.bd2.redis.DAOS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bd2.redis.dbConnection.DbConnection;
import com.bd2.redis.models.Building;
import com.bd2.redis.models.Owner;
import com.bd2.redis.models.Unit;

import redis.clients.jedis.Jedis;

public class OwnerDAO {
	private static Jedis connection;

	public OwnerDAO(){
		connection = DbConnection.getConnection();
	}
	
	public void add(Owner owner) {
		connection.sadd("owners", owner.getId());
		
		String ownersSetId = new String("owners:"+owner.getId());
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", owner.getName());
		map.put("surname", owner.getSurname());
		
		connection.hset(ownersSetId, map);
	}
	
	public void addOwner(Building building, Owner owner, Unit unit) {
		String keyOwner = new String("owners:"+owner.getId()+";owns");
		String keyVañue = new String("buildings:"+building.getId()+":units:"+unit.getId());
		connection.sadd(keyOwner, keyVañue);
	}

	public void delete(Unit unit) {
		try {
			String unitSetId = new String("owners:"+unit.getId());
			connection.del(unitSetId);
		} catch (Exception e) {
			System.out.println("No se pudo borrar el elemento.");
		}
	}

	public List<Owner> getAll() {
		
	}
	
	public Owner getOwner(String id) {
		String ownerSetId = new String("owners:"+id);
		try {
			return new Owner(
					id,
					connection.hget(ownerSetId, "name"),
					connection.hget(ownerSetId, "surname"),
					listOfUnits()
					);
		} catch (Exception e) {
			System.out.println("Probablemente no exista dicho dueño!");
		}
		return null;
	}
	
	//IMPLEMENT THIS
	public static List<Owner> getOwnersBuildingKey(String key) {
		String unitOwnerSetId = new String(key+":owners");
		List<Owner> owners = new ArrayList<Owner>();
		for(String k: connection.hkeys(unitOwnerSetId)) {
			String ownerKey = new String("owners:"+k);
			Owner owner = new Owner(
					k,
					connection.hget(ownerKey, "name"),
					connection.hget(ownerKey, "surname"),
					listOfOwn 
					);
			owners.add(owner);
		}
		
		return owners;
	}
}
