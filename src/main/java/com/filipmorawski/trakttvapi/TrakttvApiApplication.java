package com.filipmorawski.trakttvapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.filipmorawski.trakttvapi.*")
public class TrakttvApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrakttvApiApplication.class, args);
    }
}
