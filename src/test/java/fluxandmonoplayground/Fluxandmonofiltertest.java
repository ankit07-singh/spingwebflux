package fluxandmonoplayground;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

public class Fluxandmonofiltertest {

    List<String> list = Arrays.asList("ankit","piyush","aman","Ravi");

    @Test
    public void filterTest(){

        Flux<String> flux = Flux.fromIterable(list)
                .filter(s->s.startsWith("a"))
                .log();

        StepVerifier.create(flux)
                .expectNext("ankit","aman")
                .verifyComplete();
    }
}
