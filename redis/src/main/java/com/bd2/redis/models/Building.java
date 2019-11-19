package com.bd2.redis.models;

import java.io.Serializable;
import java.util.List;

public class Building implements Serializable {
	private String id;
	private String name;
	private String address;
	private List<Unit> units;
	
	public Building() { }

	public Building(String id, String name, String address, List<Unit> units) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.units = units;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Unit> getUnits() {
		return units;
	}

	public void setUnits(List<Unit> units) {
		this.units = units;
	}
}
