package com.bdd2.mongo;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.aggregation.ConvertOperators.ToString;

import com.bdd2.models.Person;

public class Transaction {
	private double Value;
	private Person asesor;
	private Person operator;
	private Date date;
	private String tradeType;
	
	public Transaction(double value, Person asesor, Person operator, Date date, String tradeType) {
		this.Value = value;
		this.asesor = asesor;
		this.operator = operator;
		this.date = date;
		this.tradeType = tradeType;
	}
	
	public Transaction(Person asesor, Person operator) {
		this.Value = generateRandValue();
		this.asesor = asesor;
		this.operator = operator;
		this.date = null;
		this.tradeType = TradeType.getTradeType();
	}
	
	private double generateRandValue() {
		Random random = new Random();
		return random.nextDouble();
	}
	
	private enum TradeType {
		Buy,Sell;
		private static String getTradeType() {
			Random random = new Random();
			return values()[random.nextInt(values().length)].toString();
		}
	}
}