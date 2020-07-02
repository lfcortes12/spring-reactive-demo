package com.gbt.spring.developer.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.gbt.spring.developer.model.Developer;
import com.gbt.spring.developer.repository.DeveloperRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@AllArgsConstructor
public class DeveloperHandler {

	private final DeveloperRepository developerRepository;

	public Mono<ServerResponse> save(final ServerRequest request) {
		Mono<Developer> developer = request.body(BodyExtractors.toMono(Developer.class))
				.flatMap(developerRepository::save);
		return ServerResponse.ok().body(BodyInserters.fromPublisher(developer, Developer.class));
	}

	public Mono<ServerResponse> findById(final ServerRequest request) {
		var id = request.pathVariable("id");
		log.info("Getting a developer by id {}", id);
		return ServerResponse.ok().body(developerRepository.findById(id), Developer.class);
	}

	public Mono<ServerResponse> findAll(final ServerRequest request) {
		log.info("Getting all developers");
		return ServerResponse.ok().body(developerRepository.findAll(), Developer.class);
	}

	public Mono<ServerResponse> searchByName(final ServerRequest request) {
		var name = request.pathVariable("name");
		log.info("Searching developers by name {}", name);
		var developerFlux = developerRepository.findByNameContaining(name);

		return ServerResponse.ok().body(BodyInserters.fromPublisher(developerFlux, Developer.class));
	}

}
