package fluxandmonoplayground;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Fluxandmonotest {

    @Test
    public void fluxTest() {
        Flux<String> stringFlux = Flux.just("Spring","Spring boot","reactive spring")
            //    .concatWith(Flux.error(new RuntimeException("Exception occurred in stringFlux")))
                .concatWith(Flux.just("After Error"))
                .log();

        stringFlux.subscribe(System.out::println,(e)->System.err.println(e),
                ()->System.out.println("Completed"));

    }

    @Test
    public void fluxTestWithoutError(){
        Flux<String> stringFlux = Flux.just("Spring","Spring boot","reactive spring")
                //    .concatWith(Flux.error(new RuntimeException("Exception occurred in stringFlux")))
               // .concatWith(Flux.just("After Error"))
                .log();
        StepVerifier.create(stringFlux)
                .expectNext("Spring")
                .expectNext("Spring boot")
                .expectNext("reactive spring")

                .verifyComplete();
    }

    @Test
    public void fluxTestWithError(){
        Flux<String> stringFlux = Flux.just("Spring","Spring boot","reactive spring")
                .concatWith(Flux.error(new RuntimeException("Exception occurred in stringFlux")))
                // .concatWith(Flux.just("After Error"))
                .log();
        StepVerifier.create(stringFlux)
                .expectNext("Spring")
                .expectNext("Spring boot")
                .expectNext("reactive spring")
                .expectError(RuntimeException.class)
                .verify();
    }

    @Test
    public void fluxTestWithElementCount(){
        Flux<String> stringFlux = Flux.just("Spring","Spring boot","reactive spring")
                .concatWith(Flux.error(new RuntimeException("Exception occurred in stringFlux")))
                // .concatWith(Flux.just("After Error"))
                .log();
        StepVerifier.create(stringFlux)
                .expectNextCount(3)
                .expectErrorMessage("Exception occurred in stringFlux")
                .verify();
    }

    @Test
    public void monoTest(){
        Mono<String> stringMono = Mono.just("Spring").log();
        StepVerifier.create(stringMono)
                .expectNext("Spring")
                .verifyComplete();
    }

    @Test
    public void monoTestWithError(){
        StepVerifier.create(Mono.error(new RuntimeException("Exception occurred")))
                .expectError(RuntimeException.class)
                .verify();
    }

}
