package com.bdd2.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public class DbConnectionUtils {
	private MongoClientURI uri;
	private MongoClient mongoClient;
	private MongoDatabase database;	
	
	private static DbConnectionUtils dbConnectionUtils;
	
	private DbConnectionUtils() {
		uri = new MongoClientURI(
				"mongodb://test:Abcde12345@dbiicluster-shard-00-00-mc1uy.mongodb.net:27017,dbiicluster-shard-00-01-mc1uy.mongodb.net:27017,dbiicluster-shard-00-02-mc1uy.mongodb.net:27017/test?ssl=true&replicaSet=DBIICluster-shard-0&authSource=admin&retryWrites=true&w=majority");

		mongoClient = new MongoClient(uri);
		database = mongoClient.getDatabase("test");	
	}
	
	public static DbConnectionUtils getConnection() {
		if (dbConnectionUtils == null) 
			dbConnectionUtils = new DbConnectionUtils();
		return dbConnectionUtils;
	}
	
	public MongoDatabase getDb() {
		return database;
	}
}
