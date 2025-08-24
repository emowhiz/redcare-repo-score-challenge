package com.example.challenge.service.score.strategy;

import com.example.challenge.config.ScoreConfig;
import com.example.challenge.model.ScoreContext;
import com.example.challenge.model.external.RepoInfo;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class UpdatedDateBasedScoreTest {
    UpdatedDateBasedScore updatedDateBasedScoreStrategy = new UpdatedDateBasedScore(new ScoreConfig.UpdatedTimeBasedScoreConfig(0.1));

    @Test
    void testUpdatedAtTodayGiveMaximumWeightedScore() {
        var repoInfo = new RepoInfo(1, "name", "xx/test", 0, 0, null, LocalDate.now());
        var score = updatedDateBasedScoreStrategy.getScore(repoInfo, new ScoreContext(LocalDate.parse("2025-01-01")));
        assertThat(score).isEqualTo(0.1);
    }
    @Test
    void testUpdatedAtEqualsCreatedAfterDayGivesZeroWeightedScore() {
        var repoInfo = new RepoInfo(1, "name", "xx/test", 0, 0, null, LocalDate.parse("2025-01-01"));
        var score = updatedDateBasedScoreStrategy.getScore(repoInfo, new ScoreContext(LocalDate.parse("2025-01-01")));
        assertThat(score).isEqualTo(0);
    }
}