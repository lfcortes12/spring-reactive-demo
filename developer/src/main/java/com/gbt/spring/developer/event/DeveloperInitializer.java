package com.gbt.spring.developer.event;

import com.gbt.spring.developer.model.Developer;
import com.gbt.spring.developer.repository.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.blockhound.BlockHound;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.Flow;
import java.util.stream.Stream;

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

       /* Flux.range(1, 10)
                .delaySequence(Duration.ofMillis(2000))
                .doOnEach(integerSignal -> {
                    log.info("On each, signal type {} for value {}", integerSignal.getType(), integerSignal.get());
                })
                .doOnNext(integer -> log.info("Counting onNext {}", integer))
                .concatWithValues(11, 12, 13)
                .concatWith(Flux.just(14, 15, 16))
                .subscribe(integer -> {
                    log.info("counting subscribe {}", integer);
                });

        Mono.fromSupplier(() -> "World")
                .doOnEach(onEach -> {log.info("On Each {}", onEach.get());})
                .doOnSuccess(s -> {
                    log.info("finishing hello world");
                }).subscribe(message -> {
                        log.info("Hello {}", message);
                });*/

    }

}