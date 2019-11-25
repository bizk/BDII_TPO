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
import redis.clients.jedis.ScanResult;

public class BuildingDAO  {
	private static Jedis connection;
	
	private static OwnerDAO ownerDAO;
	
	public BuildingDAO() {
		connection = DbConnection.getConnection();
	}
	
	public static void add(Building building) {
		connection.sadd("buildings", building.getId());
		
		String buildingSetId = new String("buildings:"+building.getId());
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", building.getName());
		map.put("address",building.getAddress());
				
		connection.hset(buildingSetId, map);
	}
	
	public void addOwner(Building building, Unit unit, Owner owner) {
		String buildingUnitOwner = new String("buildings:"+building.getId()+":units:"+unit.getId()+":owners");
		connection.sadd(buildingUnitOwner, "owners:"+owner.getId());
	}
	
	public List<Building> getAll() {
		List<Building> buildings = new ArrayList<Building>();
		for(String keyset: connection.keys("*buildings*")) {
			int kl = keyset.length();
			if (kl > 10 && kl < 14) {
				Building building = getBuilding(keyset.substring(10,kl));
				if(building != null ) {
					buildings.add(building);
				}
			}
		}
		
		return buildings;
	}
	public Building getBuilding(String id) {
		String buildingSetId = new String("buildings:"+id);
		try {
			String name = connection.hget(buildingSetId, "name");
			String address = connection.hget(buildingSetId, "address");
			return new Building(id, name, address, new ArrayList<Unit>());
		} catch (Exception e) {
			System.out.println("Probablemente no exista tal edificio");
			return null;
		}
	}
	
	public void delete(Building building) {
		try {
			String buildingSetId = new String("buildings:"+building.getId());
			connection.del(buildingSetId);
		} catch (Exception e) {
			System.out.println("No existe tal objeto");
		}
	}
	
	public static void addUnit(String building, Unit unit) {
		String buildingSetIdUnit = new String("buildings:"+building+":units");
		connection.sadd(buildingSetIdUnit, unit.getId());

		String unitSetId = new String(buildingSetIdUnit+":"+unit.getId());
		Map<String, String> map = new HashMap<String, String>();
		map.put("status", unit.getStatus());
		map.put("tenant", unit.getTenant());
		
		connection.hset(unitSetId, map);
	}
	public static void deleteUnit(String buildingid, String unitId) {
		try {
		 	String unitSetId = new String("buildings:" + buildingid + ":units:" + unitId);
		 	connection.del(unitSetId);
		} catch (Exception e) {
			System.out.println("NO existe esta unidad");
		}
	}
	public static Unit getUnit(String buildingid, String unitId) {
	 	String unitSetId = new String("buildings:" + buildingid + ":units:" + unitId);
		try {
			String status = connection.hget(unitSetId, "status");
			String tenant = connection.hget(unitSetId, "tenant");
			System.out.println(status + tenant);
			if(status == null || tenant == null) {
				System.out.println("No se encuentra dicha unidad");
				return null;
			}
			return new Unit(unitId, status, tenant, new ArrayList());
		} catch (Exception e) {
			System.out.println("NO existe esta unidad");
			return null;
		}
	}
	public static List<Unit> getAllUnits(String buildingid) {
	 	String query = new String("*buildings:" + buildingid + ":units*" );
	 	List<Unit> units = new ArrayList<Unit>();
		for(String keyset: connection.keys(query)) {
			int kl = keyset.length();
			isf (kl > 20 && kl < 23) {
				Unit unit= getUnit(buildingid, keyset.substring(20,22));
				if(unit != null ) {
					units.add(unit);
				}
			}
		}
	 	return units;
	}

	public static List<String> vista(String buildingId, String status) {
		String buildingCommands = new String("buildings:"+buildingId+":units");
		ScanResult<String> units = connection.sscan(buildingCommands, "match *");
		List<String> resul = new ArrayList();
		for(String unit: units.getResult()) {
			String unitStatus = connection.hget(buildingCommands + ":"+unit, "status");
			if(unitStatus == status) 
				resul.add(new String(buildingCommands + ":"+unit));
		}
		return resul;
	}
	
}
