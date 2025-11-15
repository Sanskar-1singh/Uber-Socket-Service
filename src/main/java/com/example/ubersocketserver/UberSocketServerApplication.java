package com.example.ubersocketserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class UberSocketServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UberSocketServerApplication.class, args);
    }

}

/**
 * STOMP protocol->simple text oriented messaging protocol
 *
 * /topic ->broker
 * /queue ->broker
 *
 *
 *
 */