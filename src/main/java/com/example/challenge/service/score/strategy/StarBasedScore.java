package com.example.challenge.service.score.strategy;

import com.example.challenge.config.ScoreConfig;
import com.example.challenge.model.ScoreContext;
import com.example.challenge.model.external.RepoInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StarBasedScore implements WeightedScoreStrategy {
    private final ScoreConfig.StarBasedScoreConfig config;

    @Override
    public double getScore(RepoInfo repoInfo, ScoreContext context) {
        return (Math.min(repoInfo.stargazersCount(), config.max()) / config.max()) * config.weight();
    }
}
