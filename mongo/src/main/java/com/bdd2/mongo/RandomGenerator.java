package com.bdd2.mongo;

import java.util.List;
import java.io.ObjectInputStream.GetField;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.Random;

import com.bdd2.models.HistoricVal;
import com.bdd2.models.Investment;
import com.bdd2.models.Opinion;
import com.bdd2.models.Person;
import com.bdd2.models.Transaction;
import com.mongodb.BasicDBObject;

public class RandomGenerator {
	private List<String> assesors;
	private List<String> operators;
	
	private static RandomGenerator instance;
	
	public static RandomGenerator getInstance() {
		if (instance == null) {
			instance = new RandomGenerator();
		} 
		return instance;
	}
	
	private  RandomGenerator() {
		this.assesors = new ArrayList<String>();
		this.operators = new ArrayList<String>();
		for(int i = 0; i < 10; i++) 
			this.assesors.add((new Person()).getNameComplete());
		
		for(int i = 0; i < 10; i++) 
			this.operators.add((new Person()).getNameComplete());	
	}
	
	public BasicDBObject generateInvestment() {
		Random random = new Random();
		
		List<BasicDBObject> historicalValues = new ArrayList<BasicDBObject>();
		for(int i=0; i < random.nextInt(100); i++) {
			historicalValues.add((new HistoricVal()).getBasicDBObject());
		}
		
		List<BasicDBObject> transactions = new ArrayList<BasicDBObject>();
		for(int i=0; i < random.nextInt(100); i++) {
			String asesor = getRandomAsesor();
			String operator = getRandomOperator();
			transactions.add((new Transaction(asesor,operator)).getBasicDBObject());
		}

		List<BasicDBObject> opinions = new ArrayList<BasicDBObject>();
		for(int i=0; i < random.nextInt(100); i++) {
			String autor = getRandomAsesor();
			opinions.add((new Opinion(autor)).getBasicDBObject());
		}

		return (new Investment(historicalValues, transactions, opinions)).getBasicDBObject();
	}

	private String getRandomAsesor() {
		Random random = new Random();
		return assesors.get(random.nextInt(assesors.size()));
	}
	
	private String getRandomOperator() {
		Random random = new Random();
		return operators.get(random.nextInt(operators.size()));
	}
	
	private List<BasicDBObject> generateHistoricValues() { 
		List<BasicDBObject> historicalValues = new ArrayList<BasicDBObject>();
		Random random = new Random();
		for (int i = 0; i < random.nextInt(100); i++) {
			Date date = new Date();
			Double price =  random.nextDouble();
			historicalValues.add(new BasicDBObject("date", date).append("price", price));
		}
		return historicalValues;
	}
	
	
}
