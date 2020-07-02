package com.gbt.spring.developer.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.gbt.spring.developer.model.Developer;
import com.gbt.spring.developer.repository.DeveloperRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Component
public class DeveloperInitializer {

    @Autowired
    private DeveloperRepository developerRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void onStartupEventListener() {
        log.info("Initializing Database");

        developerRepository.deleteAll()
                .thenMany(Flux.just("Juan", "Pepe", "Gonza", "Santi"))
                .map(name -> new Developer(null, name))
                .flatMap(developer -> developerRepository.save(developer))
                .thenMany(developerRepository.count())
                .subscribe(total ->  log.info("Total developers {}", total));

    }

}