package com.akrios.practical.joke;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JokeMain {
    public static void main(String[] args) {
        SpringApplication.run(JokeMain.class, args);
    }
}