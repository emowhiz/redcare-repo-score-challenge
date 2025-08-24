# README
This is the proposed solution for Backend Coding Challenge of Redcare Pharmacy. This service is capable of calculating
a popularity score for GitHub repositories.

## Score Calculation Logic

Min-Max based weighted scaling is used for the score calculation.

Current configuration values for max stars and max forks are extracted from here.
https://github.com/EvanLi/Github-Ranking

## How to build and run

Build the application using maven by executing the following command

``mvn clean install``

then start the service on a docker environment by running

``docker-compose up``

which starts a webserver exposed to local environment on port 8080 (http://localhost:8080 can be used to access the service).

Sample [GetScoresRequest.http](GetScoresRequest.http) is added to check the basic functionality of the service. 

## Areas for improvement

* Add token based access to extend the capabilities the search. ex: capability to search within a particular organization
* Covering more areas with the tests and writing more comprehensive tests.
* Scan for security vulnerabilities.