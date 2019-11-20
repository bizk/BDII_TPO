package com.bd2.redis.DAOS;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.bd2.redis.dbConnection.DbConnection;
import com.bd2.redis.models.Building;
import com.bd2.redis.models.Unit;

import redis.clients.jedis.Jedis;

public class BuildingDAO  {
	private Jedis connection;
	
	public BuildingDAO() {
		connection = DbConnection.getConnection();
	}
	
	public void add(Building building) {
		connection.sadd("buildings", building.getId());
		
		String buildingSetId = new String("buildings:"+building.getId());
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", building.getName());
		map.put("address",building.getAddress());
				
		connection.hset(buildingSetId, map);
		
		addUnits(building, building.getUnits());
		
	}
	
	public void addUnits(Building building, List<Unit> units) {
		String buildingSetId = new String("buildings:"+building.getId());
		if(units != null && !units.isEmpty()) {
			for(Unit unit: units) {
				connection.sadd(buildingSetId+":units", unit.getId());

				String unitSetId = new String(buildingSetId+":units:"+unit.getId());

				connection.hset(unitSetId, "status", unit.getStatus());
				connection.hset(unitSetId, "tenant", unit.getTenant());
			}
		}
	}
	
	public Building getBuilding(String id) {
		String buildingSetId = new String("buildings:"+id);
		try {
			String name = connection.hget(buildingSetId, "name");
			String address = connection.hget(buildingSetId, "address");
			
			//TODO IMPLEMENTAR ACA
			return new Building(id, name, address, null);
		} catch (Exception e) {
			System.out.println("Probablemente no exista tal edificio");
		}
		return null;
	}
	
	public void delete(String id) {
		try {
			String buildingSetId = new String("buildings:"+id);
			connection.del(buildingSetId);
		} catch (Exception e) {
			System.out.println("No existe tal objeto");
		}
	}
}
