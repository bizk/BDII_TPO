package com.bdd2.mongo;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import com.mongodb.BasicDBObject;

public class MongoDAO {

	public static BasicDBObject createInversion(String name, String type, String fdc, double actualPrice, double buyPrice,
			List<BasicDBObject> hv,List<BasicDBObject> op, List<BasicDBObject> recomendations) {
		BasicDBObject doc = new BasicDBObject("name",name)
				.append("type", type)
				.append("fdc",fdc)
				.append("actualPrice", actualPrice)
				.append("buyPrice", buyPrice)
				.append("historicVal", hv)
				.append("op", op)
				.append("recomendations", recomendations)
				;
		
		return doc;
	}
}
