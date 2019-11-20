package com.bd2.redis.models;

import java.io.Serializable;
import java.util.List;

public class Unit implements Serializable {
	private String id;
	private String status;
	private String tenant;
	private List<Owner> owners;
	
	public Unit() { }
	
	public Unit(String id, String status, String tenant, List<Owner> owners) {
		this.id = id;
		this.status = status;
		this.tenant = tenant;
		this.owners = owners;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTenant() {
		return tenant;
	}
	public void setTenant(String tenant) {
		this.tenant = tenant;
	}

	public List<Owner> getOwners() {
		return owners;
	}

	public void setOwners(List<Owner> owners) {
		this.owners = owners;
	}
	
}
