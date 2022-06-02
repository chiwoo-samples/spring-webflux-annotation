package demo.chiwoo.simple.api;

import demo.chiwoo.simple.model.FlightSchedule;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class SimpleController {

    @GetMapping("/health")
    public Mono<String> health() {
        return Mono.just("OK");
    }

    @GetMapping("/hello")
    private Mono<String> test() {
        return Mono.fromCallable(() -> "hello");
    }

    @GetMapping("/flux")
    public Flux<Integer> flux() {
        return Flux.just(1, 2, 3, 4, 5);
    }

    @GetMapping("/mono")
    public Mono<String> mono() {
        return Mono.just("1, 2, 3, 4, 5");
    }


    private DirectProcessor<FlightSchedule> processor = DirectProcessor.create();

    @GetMapping(value = "events", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<FlightSchedule> events() {
        return processor;
    }

}
