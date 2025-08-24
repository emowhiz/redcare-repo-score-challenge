package com.example.challenge.controller;

import com.example.challenge.model.ScoreResponse;
import com.example.challenge.service.score.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/score")
@RequiredArgsConstructor
public class ScoreController {
    private final ScoreService scoreService;

    @GetMapping
    public ResponseEntity<ScoreResponse> getScoresForRepos(@RequestParam(required = false) String repoName,
                                                           @RequestParam(required = false) String language,
                                                           @RequestParam(required = false) LocalDate createdAfter,
                                                           @RequestParam(required = false) Integer page) {
        return ResponseEntity.ok(scoreService.getScoresForRepo(repoName, language, createdAfter, page));
    }

}
