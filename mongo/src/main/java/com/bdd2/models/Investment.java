package com.bdd2.models;

import java.util.Date;
import java.util.List;
import java.util.Random;

import com.mongodb.BasicDBObject;

public class Investment {
	private String name;
	private String type;
	private Date fdc;
	private double actualValue;
	private double buyValue;
	private List<BasicDBObject> historicValues;
	private List<BasicDBObject> transactions;
	private List<BasicDBObject> opinions;
	
	public Investment(String name, String type, Date fdc, double actualValue, double buyValue,
			List<BasicDBObject> historicValues, List<BasicDBObject> transactions, List<BasicDBObject> opinions) {
		super();
		this.name = name;
		this.type = type;
		this.fdc = fdc;
		this.actualValue = actualValue;
		this.buyValue = buyValue;
		this.historicValues = historicValues;
		this.transactions = transactions;
		this.opinions = opinions;
	}

	public Investment(List<BasicDBObject> historicValues, List<BasicDBObject> transactions, List<BasicDBObject> opinions) {
		this.name = InvestmentName.getInvestmentName().toString();
		this.type = InvestmentType.getType().toString();
		this.fdc = null;
		this.actualValue = generateRandomValue();
		this.buyValue = generateRandomValue();
		this.historicValues = historicValues;
		this.transactions = transactions;
		this.opinions = opinions;
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

	private double generateRandomValue() {
		Random random = new Random();
		return random.nextDouble();
	}

	public BasicDBObject getBasicDBObject() {
		return new BasicDBObject("name", this.name).append("type", this.type).append("fdc", this.fdc)
				.append("actualValue", this.actualValue).append("buyValue", this.buyValue)
				.append("historicalValues", this.historicValues).append("transactions",this.transactions)
				.append("opinion", this.opinions);
	}
}
