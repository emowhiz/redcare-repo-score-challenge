package com.example.challenge.service.score.strategy;


import com.example.challenge.model.ScoreContext;
import com.example.challenge.model.external.RepoInfo;

/*
Make sure all the implementation of this strategy uses a weight to calculate the score.
And sum of the weights to be 1 for cleaner score representation.
 */
public interface WeightedScoreStrategy {
    double getScore(RepoInfo repoInfo, ScoreContext context);
}
