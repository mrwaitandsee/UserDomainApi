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
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("my")
public class MyController {
    @CrossOrigin({ "*" })
    @GetMapping(value = "test", produces = "application/stream+json")
    public Flux<Object> test() {
        return Flux
                .interval(Duration.ofSeconds(1))
                .flatMap(id -> {
                    System.out.println(id);

                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String timestamp = LocalDateTime.now().format(dateTimeFormatter);

                    System.out.println(timestamp);

                    return Mono.just(Timestamp.builder()
                            .timestamp(timestamp)
                            .build());
                });

//                .generate(sink -> {
//
//                });
    }
}