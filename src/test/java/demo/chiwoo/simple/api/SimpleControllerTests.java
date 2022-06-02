package demo.chiwoo.simple.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.hamcrest.core.StringContains.containsString;

@WebFluxTest(SimpleController.class)
@ExtendWith(SpringExtension.class)
class SimpleControllerTests {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WebTestClient client;

    @Test
    void testHealth() {

        log.info("webClient: {}", client);
        // Mockito.when(repository.save(employee)).thenReturn(Mono.just(employee));
        client.get().uri("/health")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).value(containsString("OK"));
        //Mockito.verify(repository, times(1)).save(employee);
    }


}
