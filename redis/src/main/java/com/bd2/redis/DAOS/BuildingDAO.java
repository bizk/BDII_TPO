package com.bd2.redis.DAOS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.bd2.redis.dbConnection.DbConnection;
import com.bd2.redis.models.Building;
import com.bd2.redis.models.Owner;
import com.bd2.redis.models.Unit;

import redis.clients.jedis.Jedis;

public class BuildingDAO  {
	private Jedis connection;
	
	private static OwnerDAO ownerDAO;
	
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
		
		for(Unit unit: building.getUnits()) {
			addUnit(building, unit);
		}
	}
	
	public void addUnit(Building building, Unit unit) {
		String buildingSetIdUnit = new String("buildings:"+building.getId()+":units");
		connection.sadd(buildingSetIdUnit, unit.getId());

		String unitSetId = new String(buildingSetIdUnit+":"+unit.getId());
		Map<String, String> map = new HashMap<String, String>();
		map.put("status", unit.getStatus());
		map.put("tenant", unit.getTenant());
		
		connection.hset(unitSetId, map);
	}
	
	public void addOwner(Building building, Unit unit, Owner owner) {
		String buildingUnitOwner = new String("buildings:"+building.getId()+":units:"+unit.getId()+":owners");
		connection.sadd(buildingUnitOwner, "owners:"+owner.getId());
	}
	
	public List<Building> getAll() {
		List<Building> buildings = new ArrayList<Building>();
		for(String key: connection.hkeys("buildings")) {
			buildings.add(getBuilding(key));
		}
		return buildings;
	}
	
	public Building getBuilding(String id) {
		String buildingSetId = new String("buildings:"+id);
		try {
			String name = connection.hget(buildingSetId, "name");
			String address = connection.hget(buildingSetId, "address");
			return new Building(id, name, address, listOfUnits(id));
		} catch (Exception e) {
			System.out.println("Probablemente no exista tal edificio");
		}
		return null;
	}
	
	public void delete(Building building) {
		try {
			String buildingSetId = new String("buildings:"+building.getId());
			connection.del(buildingSetId);
		} catch (Exception e) {
			System.out.println("No existe tal objeto");
		}
	}

	private List<Unit> listOfUnits(String id) {
		String buildingSetId = new String("buildings:"+id);
		List<Unit> units = new ArrayList<Unit>();
		for(String key: connection.hkeys(buildingSetId+":units")) {
			String unitKey = new String(buildingSetId+":units:"+key);
			Unit unit = new Unit(
					key,
					connection.hget(unitKey, "status"),
					connection.hget(unitKey, "tenant"),
					OwnerDAO.getOwnersByBuildingKey(unitKey)
					);			
			units.add(unit);
		}
		return units;
	}
}
