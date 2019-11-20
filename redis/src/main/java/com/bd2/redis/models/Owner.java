package com.bd2.redis.models;

import java.io.Serializable;
import java.util.List;

public class Owner implements Serializable {
	private String id;
	private String name;
	private String surname;
	private List<Unit> owns;
	
	public Owner() {
		super();
	}

	public Owner(String id, String name, String surname, List<Unit> owns) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.owns = owns;
	}

	public String getId() {
		return this.id;
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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public List<Unit> getOwns() {
		return owns;
	}

	public void setOwns(List<Unit> owns) {
		this.owns = owns;
	}
	
}
