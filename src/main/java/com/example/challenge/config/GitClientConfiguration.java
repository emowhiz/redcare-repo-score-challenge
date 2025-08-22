package com.example.challenge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class GitClientConfiguration {

    @Bean
    public RestClient restTemplate() {
        return RestClient.builder()
                .baseUrl("https://api.github.com")
                .build();
    }


}
