package com.gbt.spring.developer.config;

import org.testcontainers.containers.MongoDBContainer;

public class CustomMongoDBContainer extends MongoDBContainer {

	private static final String IMAGE_VERSION = "mongo:4.0.10";
	private static CustomMongoDBContainer container;

	private CustomMongoDBContainer() {
		super(IMAGE_VERSION);
	}

	public static CustomMongoDBContainer getInstance() {
		if (container == null) {
			container = new CustomMongoDBContainer();
		}
		return container;
	}

	@Override
	public void start() {
		super.start();
		System.setProperty("DB_REPLICA_SET_URL", container.getReplicaSetUrl());
	}

	@Override
	public void stop() {
		// do nothing, JVM handles shut down
	}

}
