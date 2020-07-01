package com.gbt.spring.developer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;


@EnableReactiveMongoRepositories
@SpringBootApplication
public class DeveloperApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeveloperApplication.class, args);
	}	

}
