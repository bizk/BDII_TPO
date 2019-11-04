package com.bdd2.models;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.aggregation.ConvertOperators.ToString;

import com.mongodb.BasicDBObject;

public class Transaction implements DBObjectInterface {
	private double value;
	private String asesor;
	private String operator;
	private Date date;
	private String tradeType;
	
	public Transaction(double value, String asesor, String operator, Date date, String tradeType) {
		this.value = value;
		this.asesor = asesor;
		this.operator = operator;
		this.date = date;
		this.tradeType = tradeType;
	}
	
	public Transaction(String asesor, String operator) {
		this.value = generateRandValue();
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

	@Override
	public BasicDBObject getBasicDBObject() {
		return new BasicDBObject("value", this.value).append("asesor", this.asesor).append("operator", this.operator)
				.append("date", this.date).append("tradeType", this.tradeType);
	}
	
	
}