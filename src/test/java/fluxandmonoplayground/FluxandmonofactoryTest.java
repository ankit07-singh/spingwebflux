package fluxandmonoplayground;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class FluxandmonofactoryTest {

    List<String> list = Arrays.asList("Ankit","piyush","aman","Ravi");

    @Test
    public void fluxWithIterable(){

        Flux<String> namesFlux = Flux.fromIterable(list);

        StepVerifier.create(namesFlux)
                .expectNext("Ankit","piyush","aman","Ravi")
                .verifyComplete();
    }

    @Test
    public void fluxUsingFromArray(){
        String[] array = new String[]{"Ankit","piyush","aman","Ravi"};

        Flux<String> flux = Flux.fromArray(array);
        StepVerifier.create(flux)
                .expectNext("Ankit","piyush","aman","Ravi")
                .verifyComplete();
    }

    @Test
    public void fluxUsingStream(){
        Flux<String> flux = Flux.fromStream(list.stream());
        StepVerifier.create(flux)
                .expectNext("Ankit","piyush","aman","Ravi")
                .verifyComplete();
    }

    @Test
    public void monousingJustorEmpty(){
        Mono<String> mono = Mono.justOrEmpty(null);//returns mono.empty
        StepVerifier.create(mono.log())
                .verifyComplete();
    }

    @Test
    public void monoUsingSupplier(){

        Supplier<String> supplier = ()->"Ankit";
        Mono<String> stringMono = Mono.fromSupplier(supplier);
        System.out.println(supplier.get());
        StepVerifier.create(stringMono.log())
                .expectNext("Ankit")
                .verifyComplete();
    }

    @Test
    public void fluxUisngRange(){
        
        Flux<Integer> flux = Flux.range(1,5);
        StepVerifier.create(flux)
                .expectNext(1,2,3,4,5)
                .verifyComplete();
    }
}
