package com.bdd2.mongo;

import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDAO {
	private static MongoDatabase db;
	private static MongoCollection<Document> inversionesDb;
	public MongoDAO() {
		/*MongoClientURI uri = new MongoClientURI(
		    "mongodb+srv://dbUser:Abcde12345!@dbiicluster-mc1uy.mongodb.net/test?retryWrites=true&w=majority");
		

		MongoClient mongo = new MongoClient(uri);*/
		
		//		MongoDatabase database = mongoClient.getDatabase("test");
		MongoClient mongo = new MongoClient();
		db = mongo.getDatabase("bdII_1");
		inversionesDb = db.getCollection("inversiones");
	}
	
	public static void vista1() {
		AggregateIterable<Document> documents = inversionesDb.aggregate(Arrays.asList(
				new Document("$project", 
						new Document("nombre",1).append("cantidadOperaciones", 
								new Document("$size", "$operaciones")
						)
					),
				new Document("$sort", new Document("nombre", 1)),
				new Document("$sort", 
						new Document("cantidadOperaciones",-1)
					),
				new Document("$project", 
						new Document("_id",1).append("nombre", 1).append("cantidadOperaciones", 1)),
				new Document("$limit",1)
				));
		for(Document doc: documents){
			System.out.println(doc);
		}
	}
	
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
		inversionesDb.insertOne(new Document(inversion.getBasicDBObject().toMap()));
	}
	
	public static void deleteInversion(String name) {
		try {
			inversionesDb.findOneAndDelete(new BasicDBObject("nombre",name));
		} catch (Exception e) {
			System.out.println("No existe dicha inversion");
		}
	}

	public static void findAll() {
		FindIterable<Document> cursor = inversionesDb.find();
		for(Document doc: cursor) 
			System.out.println(doc);
	}
	
	public static void findInversion(String name) {
		BasicDBObject query = new BasicDBObject("nombre", name);
		FindIterable<Document> cursor = inversionesDb.find(query);
		for(Document doc: cursor) 
			System.out.println(doc);
	}
}
