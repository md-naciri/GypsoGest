package com.filrouge.gypsogest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)

public class GypsoGestApplication {

    public static void main(String[] args) {
        SpringApplication.run(GypsoGestApplication.class, args);
    }

}