package com.bdd2.mongo;

import java.util.List;
import java.io.ObjectInputStream.GetField;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.Random;

import com.mongodb.BasicDBObject;

public class RandomGenerator {
	public static BasicDBObject generateInvestment() {
		BasicDBObject investment = new BasicDBObject();
		
		List<BasicDBObject> op = new ArrayList<BasicDBObject>();
		List<BasicDBObject> rec = new ArrayList<BasicDBObject>();
		return MongoDAO.createInversion(InvestmentName.getInvestmentName().toString(), InvestmentType.getType().toString(),
				null, getRandomDouble(), getRandomDouble(), generateHistoricValues(), op, rec);
	}

	private enum InvestmentName {
		gogle, ipf, pmgorgan, freemarket, appe, hintel, masung, tomorola, ecorp, blul4gr4ng3, idkimakiwi, stream,
		vlizard, uwuinc, bmd, bizkcorp;

		public static InvestmentName getInvestmentName() {
			Random random = new Random();
			return values()[random.nextInt(values().length)];
		}
	}

	private enum InvestmentType {
		Stock, Bond, Future, MutualFund, IndexFund, Option;

		public static InvestmentType getType() {
			Random random = new Random();
			return values()[random.nextInt(values().length)];
		}
	}

	private static double getRandomDouble() {
		Random random = new Random();
		return random.nextDouble();
	}
	
	private static List<BasicDBObject> generateHistoricValues() { 
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
