package com.example.challenge.service.score;

import com.example.challenge.model.ScoreContext;
import com.example.challenge.model.external.RepoInfo;
import com.example.challenge.service.score.strategy.ForkBasedScore;
import com.example.challenge.service.score.strategy.StarBasedScore;
import com.example.challenge.service.score.strategy.UpdatedDateBasedScore;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDate;

@SpringBootTest(classes = ScoreCalculator.class)
class ScoreCalculatorTest {
    @MockitoBean
    ForkBasedScore forkBasedScoreStrategy;
    @MockitoBean
    UpdatedDateBasedScore updatedDateBasedScoreStrategy;
    @MockitoBean
    StarBasedScore starBasedScoreStrategy;

    @Autowired
    ScoreCalculator scoreCalculator;

    @Test
    void calculateScoreShouldCallAllStrategies() {
        var repoInfo = new RepoInfo(1, "name", "xx/test", 10, 0, null, LocalDate.now());
        ScoreContext context = new ScoreContext(LocalDate.now());
        scoreCalculator.calculateScore(repoInfo, context);
        Mockito.verify(forkBasedScoreStrategy, Mockito.times(1)).getScore(repoInfo, context);
        Mockito.verify(updatedDateBasedScoreStrategy, Mockito.times(1)).getScore(repoInfo, context);
        Mockito.verify(starBasedScoreStrategy, Mockito.times(1)).getScore(repoInfo, context);
    }
}