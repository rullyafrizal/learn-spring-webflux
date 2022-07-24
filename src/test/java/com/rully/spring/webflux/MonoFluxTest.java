package com.rully.spring.webflux;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class MonoFluxTest {

    @Test
    public void testMono() {
        Mono<?> rully = Mono.just("Rully")
            .then(Mono.error(new RuntimeException("Exception occured")))
            .log();

        rully.subscribe(System.out::println, e -> System.out.println(e.getMessage()));
    }

    @Test
    void testFlux() throws InterruptedException {
        Flux<String> just = Flux.just("Rully", "Afrizal", "Alwin")
            .map(String::toUpperCase)
            .delayElements(Duration.ofMillis(1000))
            .log();

        just.subscribe(System.out::println);

        Thread.sleep(3100L);
    }

}
