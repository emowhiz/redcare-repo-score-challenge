package com.example.challenge.model;

import java.util.List;

public record ScoreResponse(long totalCount, List<RepoScore> scores) {
}
