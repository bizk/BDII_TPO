package com.bdd2.mongo;

import java.awt.geom.GeneralPath;
import java.io.IOException;
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
		Controlador ctrl = new Controlador();
		try {
			ctrl.init();
		} catch (IOException e) {
			System.out.println("algo salio mal!");
			e.printStackTrace();
		}
	}
}
