package com.gbt.spring.developer.config;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.gbt.spring.developer.model.Developer;
import com.gbt.spring.developer.repository.DeveloperRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class DeveloperRouterConfig {

	@Bean
	RouterFunction<ServerResponse> developerSearchRoutes(final DeveloperRepository developerRepository) {
		log.info("Setting up developer search routes");

		return route()
				.GET("/developers", request -> ServerResponse.ok().body(developerRepository.findAll(), Developer.class))
				.build();
	}
}
