package com.gbt.spring.developer.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.gbt.spring.developer.model.Developer;

import reactor.core.publisher.Flux;

@Repository
public interface DeveloperRepository extends ReactiveCrudRepository<Developer, String> {
	
	Flux<Developer> findByNameContaining(String name);
	
}
