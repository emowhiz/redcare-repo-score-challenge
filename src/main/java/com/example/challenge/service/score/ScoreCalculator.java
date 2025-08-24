package com.example.challenge.service.score;

import com.example.challenge.model.ScoreContext;
import com.example.challenge.model.external.RepoInfo;
import com.example.challenge.service.score.strategy.WeightedScoreStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScoreCalculator {
    private final List<WeightedScoreStrategy>  strategies;
    public double calculateScore(RepoInfo repoInfo, ScoreContext context) {
        return strategies.stream().map(strategy -> {
            var score = strategy.getScore(repoInfo, context);
            log.info("strategy : {} weighted-score: {}",strategy.getClass().getSimpleName(),score);
            return score;
        }).reduce(0.0, Double::sum);
    }
}
