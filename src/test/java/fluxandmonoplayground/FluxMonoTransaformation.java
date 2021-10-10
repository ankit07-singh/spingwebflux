package fluxandmonoplayground;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

public class FluxMonoTransaformation {

    List<String> list = Arrays.asList("ankit","piyush","aman","Ravi");

    @Test
    public  void fluxTransaform(){
        Flux<String> names = Flux.fromIterable(list)
                .map(s->s.toUpperCase())
                .log();

        StepVerifier.create(names)
                .expectNext("ANKIT","PIYUSH","AMAN","RAVI")
                .verifyComplete();
    }

    @Test
    public  void fluxTransaform_Lenght(){
        Flux<Integer> names = Flux.fromIterable(list)
                .map(s->s.length())
                .log();

        StepVerifier.create(names)
                .expectNext(4,4,4,5)
                .verifyComplete();
    }

    @Test
    public  void fluxTransaform_Lenght_Repeat(){
        Flux<Integer> names = Flux.fromIterable(list)
                .map(s->s.length())
                .repeat(1)
                .log();

        StepVerifier.create(names)
                .expectNext(4,4,4,5,4,4,4,5)
                .verifyComplete();
    }
}
