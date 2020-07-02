package com.gbt.spring.developer.router;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.ClassRule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import com.gbt.spring.developer.config.CustomMongoDBContainer;
import com.gbt.spring.developer.model.Developer;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class DeveloperRouterTest {
	
	@ClassRule
	static CustomMongoDBContainer mongoContainer = CustomMongoDBContainer.getInstance();
	
	@BeforeAll
    public static void startContainerAndPublicPortIsAvailable() {
		mongoContainer.start();
    }
	
	@Test
	void findAllDevelopersTest(@Autowired WebTestClient webClient) {
		webClient.get().uri("/developer").accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isOk()
				.expectBodyList(Developer.class).hasSize(4);

	}

	@Test
	void createDevelopersTest(@Autowired WebTestClient webClient) {
		var dev = Developer.builder().name("testname").build();
		webClient.post().uri("/developer").accept(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(dev))
				.exchange().expectStatus().isOk().expectBody(Developer.class)
				.value(developer -> {
					assertThat(developer.getId()).isNotEmpty();
					assertThat(developer.getName()).isEqualTo("testname");
				});
	}

}
