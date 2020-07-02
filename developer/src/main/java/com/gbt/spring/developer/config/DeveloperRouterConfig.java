package com.gbt.spring.developer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.gbt.spring.developer.handler.DeveloperHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class DeveloperRouterConfig {

	@Bean
	RouterFunction<ServerResponse> developerRoutes(final DeveloperHandler developerHandler) {
		log.info("Setting up developer routes");

		return RouterFunctions.route(RequestPredicates.GET("/developer"), developerHandler::findAll)
				.andRoute(RequestPredicates.GET("/developer/search/name/{name}"), developerHandler::searchByName)
				.andRoute(RequestPredicates.GET("/developer/{id}"), developerHandler::findById)
				.andRoute(RequestPredicates.POST("/developer"), developerHandler::save);
	}

}
