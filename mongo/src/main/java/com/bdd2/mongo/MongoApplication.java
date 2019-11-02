package com.bdd2.mongo;

import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoDatabase;

@SpringBootApplication
public class MongoApplication {
	public static void main(String[] args) {
		// SpringApplication.run(MongoApplication.class, args);
		// MongoDatabase db = DbConnectionUtils.getConnection().getDb();
		// System.out.println(db.getName());

		List<BasicDBObject> historicValues = new ArrayList<BasicDBObject>();
		historicValues.add(new BasicDBObject("date", "-1").append("date2", "-2"));
		historicValues.add(new BasicDBObject("date", "3").append("date2", "124"));

		List<BasicDBObject> op = new ArrayList<BasicDBObject>();
		op.add(new BasicDBObject("a", "-1").append("b", "-2"));
		op.add(new BasicDBObject("a", "3").append("b", "124"));

		List<BasicDBObject> rec = new ArrayList<BasicDBObject>();
		rec.add(new BasicDBObject("c", "-1").append("d", "-2"));
		rec.add(new BasicDBObject("c", "3").append("d", "124"));

		MongoDAO.createInversion("carlos", "XD", "-1", 123.12, 50.42, historicValues, op, rec);

		for (int i = 0; i < 10; i++)
			System.out.println(RandomGenerator.generateInvestment());
	}
}
