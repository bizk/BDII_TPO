package com.bdd2.models;

import java.util.Date;
import java.util.Random;

import com.mongodb.BasicDBObject;

public class HistoricVal implements DBObjectInterface {
	private Date date;
	private double price;
	
	public HistoricVal(Date date, double price) {
		super();
		this.date = date;
		this.price = price;
	}

	public HistoricVal() {
		super();
		this.date = new Date();
		this.price = generatePrice();
	}

	private double generatePrice() {
		Random random = new Random();
		return random.nextDouble();
	}

	@Override
	public BasicDBObject getBasicDBObject() {
		return new BasicDBObject("date", this.date).append("price", this.price);
	}
}

