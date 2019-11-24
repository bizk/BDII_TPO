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

import controlador.Controlador;

@SpringBootApplication
public class MongoApplication {
	public static void main(String[] args) {
		// SpringApplication.run(MongoApplication.class, args);
		// MongoDatabase db = DbConnectionUtils.getConnection().getDb();
		// System.out.println(db.getName());
	
		Controlador ctrl = new Controlador();
		ctrl.init();
	}
}
