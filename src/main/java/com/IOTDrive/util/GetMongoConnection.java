package com.IOTDrive.util;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import java.io.IOException;

public class GetMongoConnection {
	private static MongoClient connection;
	private static DB dbConnection;

	private GetMongoConnection() {

	}

	public static DB getConnection() throws IOException {

		if (dbConnection == null) {
			
			MongoClientURI uri = new MongoClientURI("Mongo URI");
			connection = new MongoClient(uri);
			dbConnection = connection.getDB("mongo");
		}
		return dbConnection;

	}

	public static void closeConnection() {
		connection.close();
	}

}
