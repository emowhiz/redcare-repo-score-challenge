package com.example.challenge.service.score;


import com.example.challenge.config.ScoreConfig;
import com.example.challenge.model.RepoScore;
import com.example.challenge.model.ScoreContext;
import com.example.challenge.model.ScoreResponse;
import com.example.challenge.model.external.RepoInfo;
import com.example.challenge.service.SearchGitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScoreService {
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final SearchGitService searchGitService;
    private final ScoreConfig scoreConfig;
    private final ScoreCalculator scoreCalculator;

    public ScoreResponse getScoresForRepo(String repoName, String language, LocalDate createdAfter, Integer page) {
        if (Objects.isNull(language)) {
            language = scoreConfig.getDefaultLanguage();
        }
        if (Objects.isNull(createdAfter)) {
            createdAfter = scoreConfig.getDefaultCreatedAfter();
        }
        var searchGitResponse = searchGitService.searchForGitRepos(repoName, language, DATE_FORMATTER.format(createdAfter), page);
        var finalCreatedAfter = createdAfter;
        var scoredItems = searchGitResponse.items().stream()
                .map(repoInfo -> {
                    var score = scoreCalculator.calculateScore(repoInfo, new ScoreContext(finalCreatedAfter));
                    log.info("Score for {} is {}", repoInfo, score);
                    return new RepoScore(repoInfo.id(), repoInfo.name(), repoInfo.fullName(), score);
                })
                .toList();
        return new ScoreResponse(searchGitResponse.totalCount(), scoredItems);
    }

}
