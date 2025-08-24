package com.example.challenge.service;

import com.example.challenge.model.external.SearchGitResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class SearchGitService {
    private final RestClient restClient;

    public SearchGitResponse searchForGitRepos(String repoName, String language, String createdAfter, Integer page) {
        return restClient.get()
                .uri("/search/repositories",
                        uriBuilder -> uriBuilder
                                .queryParam("q", repoName + " language:" + language + " created:>" + createdAfter)
                                .queryParam("page", page)
                                .build())
                .retrieve()
                .body(SearchGitResponse.class);
    }
}
