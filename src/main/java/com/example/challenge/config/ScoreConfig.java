package com.example.challenge.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@Configuration
@EnableConfigurationProperties({
        ScoreConfig.ForkBasedScoreConfig.class,
        ScoreConfig.StarBasedScoreConfig.class,
        ScoreConfig.UpdatedTimeBasedScoreConfig.class
})
@ConfigurationProperties("score")
public class ScoreConfig {

    private String defaultLanguage;
    private LocalDate defaultCreatedAfter;

    @ConfigurationProperties("score.fork")
    public record ForkBasedScoreConfig(double weight, float max){}
    @ConfigurationProperties("score.star")
    public record StarBasedScoreConfig(double weight, float max){}
    @ConfigurationProperties("score.updated")
    public record UpdatedTimeBasedScoreConfig(double weight){}

}
