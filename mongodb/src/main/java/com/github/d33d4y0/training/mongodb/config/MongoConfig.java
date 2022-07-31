package com.github.d33d4y0.training.mongodb.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
@PropertySource("file:${mongo.config.path}")
public class MongoConfig extends AbstractMongoClientConfiguration {

	@Value("${mongodb.host}")
	private String host;

	@Value("${mongodb.database}")
	private String database;

	@Override
	protected String getDatabaseName() {
		return database;
	}

	@Override
	public MongoClient mongoClient() {
		ConnectionString connectionString = new ConnectionString(host + database);
//		MongoCredential cred = MongoCredential.createCredential(username, database, password.toCharArray());
		MongoClientSettings mongoClientSettings = MongoClientSettings.builder().applyConnectionString(connectionString)
				.build();
		return MongoClients.create(mongoClientSettings);
	}
}
