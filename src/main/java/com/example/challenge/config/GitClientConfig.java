package com.example.challenge.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.http.client.ClientHttpRequestFactoryBuilder;
import org.springframework.boot.http.client.ClientHttpRequestFactorySettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import java.time.Duration;

@Configuration
@EnableConfigurationProperties(GitClientConfig.GitProperties.class)
public class GitClientConfig {

    @Bean
    public RestClient restClient(GitProperties properties, ClientHttpRequestFactory clientHttpRequestFactory) {
        return RestClient.builder()
                .baseUrl(properties.baseUrl())
                .requestFactory(clientHttpRequestFactory)
                .build();
    }

    @Bean
    public ClientHttpRequestFactory clientHttpRequestFactory(GitProperties properties) {

        return ClientHttpRequestFactoryBuilder.simple()
                .build(ClientHttpRequestFactorySettings.defaults()
                        .withConnectTimeout(properties.connectTimeout)
                        .withReadTimeout(properties.readTimeout()));
    }

    @ConfigurationProperties(prefix = "git")
    public record GitProperties(String baseUrl, Duration connectTimeout, Duration readTimeout) {
    }
}
