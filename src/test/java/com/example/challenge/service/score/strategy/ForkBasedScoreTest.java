package com.example.challenge.service.score.strategy;

import com.example.challenge.config.ScoreConfig;
import com.example.challenge.model.external.RepoInfo;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ForkBasedScoreTest {
    ForkBasedScore forkBasedScoreStrategy = new ForkBasedScore(new ScoreConfig.ForkBasedScoreConfig(0.5, 10));

    @Test
    void testMaxValueGiveMaximumWeightedScore() {
        var repoInfo = new RepoInfo(1, "name", "xx/test", 10, 0, null, null);
        var score = forkBasedScoreStrategy.getScore(repoInfo, null);
        assertThat(score).isEqualTo(0.5);
    }
    @Test
    void testValuesOverMaxValueGiveMaximumWeightedScore() {
        var repoInfo = new RepoInfo(1, "name", "xx/test", 200, 0, null, null);
        var score = forkBasedScoreStrategy.getScore(repoInfo, null);
        assertThat(score).isEqualTo(0.5);
    }
    @Test
    void testZeroForksGivesZeroWeightedScore() {
        var repoInfo = new RepoInfo(1, "name", "xx/test", 0, 0, null, null);
        var score = forkBasedScoreStrategy.getScore(repoInfo, null);
        assertThat(score).isEqualTo(0);
    }
    @Test
    void testForProperValue() {
        var repoInfo1 = new RepoInfo(1, "name", "xx/test", 5, 0, null, null);
        var score1 = forkBasedScoreStrategy.getScore(repoInfo1, null);
        assertThat(score1).isEqualTo(0.25);
    }
}