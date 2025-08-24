package com.example.challenge.service.score.strategy;

import com.example.challenge.config.ScoreConfig;
import com.example.challenge.model.ScoreContext;
import com.example.challenge.model.external.RepoInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
@RequiredArgsConstructor
public class UpdatedDateBasedScore implements WeightedScoreStrategy {
    private final ScoreConfig.UpdatedTimeBasedScoreConfig config;

    @Override
    public double getScore(RepoInfo repoInfo, ScoreContext context) {
        var currentDate = LocalDate.now();
        var daysFromCreatedAfter = context.createdAfter().until(currentDate, ChronoUnit.DAYS);
        return (1 - (Math.max(repoInfo.updatedAt().until(currentDate, ChronoUnit.DAYS), 0) / (daysFromCreatedAfter * 1.0))) * config.weight();
    }
}
