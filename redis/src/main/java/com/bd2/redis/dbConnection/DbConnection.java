package com.bd2.redis.dbConnection;

import redis.clients.jedis.Jedis;

public class DbConnection {
	private static Jedis connection;

	public static Jedis getConnection() {
		if (connection == null) {
			try {
				connection = new Jedis("localhost");
				System.out.println("Connection successful");
				return connection;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			return connection;
		}
		return null;
	}
}
