package com.filrouge.gypsogest.database.seeder;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DatabaseSeeder {

    private final UserSeeder userSeeder;

    @PostConstruct
    public void init(){
        userSeeder.seed();
    }
}
