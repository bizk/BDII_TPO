package com.bd2.redis.models;

import java.io.Serializable;
import java.util.List;

public class Owner implements Serializable {
	private String id;
	private String name;
	private String surname;
	private List<String> owns; //String of units id
	
	public Owner() {
		super();
	}

	public Owner(String id, String name, String surname, List<String> owns) {
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

	public List<String> getOwns() {
		return owns;
	}

	public void setOwns(List<String> owns) {
		this.owns = owns;
	}
	
}
