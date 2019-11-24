package com.bdd2.mongo;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import com.bdd2.models.Investment;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.WriteResult;

public class MongoDAO {
	private static DB db;
	private static DBCollection inversionesDb;
	public MongoDAO() {
		/*MongoClientURI uri = new MongoClientURI(
		    "mongodb+srv://dbUser:Abcde12345!@dbiicluster-mc1uy.mongodb.net/test?retryWrites=true&w=majority");
		

		MongoClient mongo = new MongoClient(uri);*/
		
		//		MongoDatabase database = mongoClient.getDatabase("test");
		MongoClient mongo = new MongoClient();
		db = mongo.getDB("bdII");
		inversionesDb = db.getCollection("inversiones");
	}
	
	/*
	public static BasicDBObject createInversion(Investment inversion) {
		BasicDBObject doc = new BasicDBObject("nombre",inversion.get)
				.append("tipo", type)
				.append("fdc",fdc)
				.append("PrecioActual", actualPrice)
				.append("precioCompra", buyPrice)
				.append("valorHistorico", hv)
				.append("op", op)
				.append("recomendations", recomendations);
		
		return doc;
	}
	*/
	
	public static BasicDBObject createHistoricValue(Date date, float precio) {
		BasicDBObject doc = new BasicDBObject("fecha", date)
				.append("precio", precio);
		return doc;
	}
	
	public static BasicDBObject createOperation(float importe, String asesor, String operador,
			Date fecha, String tipo) {
		BasicDBObject doc = new BasicDBObject("importe", importe)
				.append("asesor", asesor).append("operador", operador)
				.append("fecha", fecha).append("tipo", tipo);
		return doc;
	}
	
	public static BasicDBObject createRecomendacion(String autor, Date fecha, String situacionActual,
			String factoresExternos, String futuro, String recomendacion) {
		BasicDBObject doc = new BasicDBObject("autor", autor).append("fecha", fecha)
				.append("situacionActual", situacionActual).append("factoresExternos", factoresExternos)
				.append("futuro", futuro).append("recomendacion", recomendacion);
		return doc;
	}

	public static void addInversion(Investment inversion) {
		WriteResult result = inversionesDb.insert(inversion.getBasicDBObject());
		System.out.println(result.getUpsertedId());
		System.out.println(result.getN());
		System.out.println(result.isUpdateOfExisting());
	}
	
	public static void deleteInversion(String name) {
		try {
			DBObject doc = inversionesDb.findOne(new BasicDBObject("name",name));
			WriteResult result = inversionesDb.remove(doc);
		} catch (Exception e) {
			System.out.println("No existe dicha inversion");
		}
	}

	public static void findAll() {
		DBCursor cursor = inversionesDb.find();
		try {
			while(cursor.hasNext()) {
				System.out.println(cursor.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static BasicDBObject findInversion(String name) {
		BasicDBObject query = new BasicDBObject("name", name);
		DBCursor cursor = inversionesDb.find(query);
		try {
			while(cursor.hasNext()) {
				System.out.println(cursor.next());
			}
		} catch (Exception e) {
			System.out.println("No existe tal inversione");
		}
		return null;
	}
}
