package com.example.challenge.model.external;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.LocalDate;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record RepoInfo(long id,
                       String name,
                       String fullName,
                       int forksCount,
                       int stargazersCount,
                       LocalDate createdAt,
                       LocalDate updatedAt) {
}
