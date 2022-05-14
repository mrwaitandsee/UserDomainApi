package mrwaitandsee.userdomainapi.controller;

import mrwaitandsee.userdomainapi.entity.Timestamp;
import org.reactivestreams.Publisher;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.*;
import java.time.Duration;
import java.time.LocalDateTime;

@RestController
@RequestMapping("my")
public class MyController {
    @CrossOrigin({ "*" })
    @GetMapping(value = "test", produces = "application/stream+json")
    public Flux<Timestamp> test() {
        return Flux.generate(
                sink -> sink.next(Timestamp.builder()
                        .timestamp(LocalDateTime.now())
                        .build()))
                .delayElements(Duration.ofSeconds(1))
                .flatMap(o -> Mono.just((Timestamp) o));
    }
}