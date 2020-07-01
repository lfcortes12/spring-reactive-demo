package com.gbt.spring.developer.repository;

import com.gbt.spring.developer.model.Developer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeveloperRepository extends ReactiveCrudRepository<Developer, String> {




}
