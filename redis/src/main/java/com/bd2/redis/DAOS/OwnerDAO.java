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
	public void delete(String ownerId) {
		try {
			String unitSetId = new String("owners:"+ownerId);
			connection.del(unitSetId);
		} catch (Exception e) {
			System.out.println("No se pudo borrar el elemento.");
		}
	}
	public List<Owner> getAll() {
		List<Owner> owners = new ArrayList<Owner>();
		for(String keyset: connection.keys("*owners*")) {
			int kl = keyset.length();
			if (kl > 7 && kl < 11) {
				Owner owner = getOwner(keyset.substring(8,kl));
				if(owner != null ) {
					owners.add(owner);
				}
			}
		}
		
		return owners;
	}
	public Owner getOwner(String id) {
		String ownerSetId = new String("owners:"+id);
		try {
			return new Owner(
					id,
					connection.hget(ownerSetId, "name"),
					connection.hget(ownerSetId, "surname"),
					new ArrayList<String>()
					);
		} catch (Exception e) {
			System.out.println("Probablemente no exista dicho dueño!");
		}
		return null;
	}
	
	//IMPLEMENT THIS
	public void addOwnsBuilding(String building, String owner, String unit) {
		String keyOwner = new String("owners:"+owner+";owns");
		String keyValue = new String("buildings:"+building+":units:"+unit);
		connection.sadd(keyOwner, keyValue);
		connection.sadd(new String(keyValue + ":owners"), new String("owners:"+owner));
	}
	
	public void removeOwnsBuilding(String building, String owner, String unit) {
		String keyOwner = new String("owners:"+owner+";owns");
		connection.del(keyOwner);
		String keyValue = new String("buildings:"+building+":units:"+unit+":owners");
		connection.del(keyValue);
	}
	
	public static List<Owner> getOwnersByBuildingKey (String key) {
		String unitOwnerSetId = new String(key+":owners");
		List<Owner> owners = new ArrayList<Owner>();
		for(String k: connection.hkeys(unitOwnerSetId)) {
			String ownerKey = new String("owners:"+k);
			Owner owner = new Owner(
					k,
					connection.hget(ownerKey, "name"),
					connection.hget(ownerKey, "surname"),
					ownedUnits(k) 
					);
			owners.add(owner);
		}
		return owners;
	}
	
	private static List<String> ownedUnits(String id) {
		String ownerStr = new String("owners:"+id+":owns");
		List<String> units = new ArrayList<String>();
		for(String unitId: connection.hkeys(ownerStr)) 
			units.add(unitId);
		return units;
	}
}
