# Movie DB
This is recruitment task that includes simple movie API using Spring Boot with JWT auth.
 
## Functional requirements
* Users should be able to register themselves using login and password and receive access token to the movie database
* Users should be able to remove their account
* User should be able to add new entries about movies: title, description and watched flag
* Users should be able to list all entries
* Users should be able to filter movies by watched flag (all, watched, unwatched)
* Users should be able to edit movies
* Users should be able to delete movies

## Non-functional requirements
* All movie database endpoints should be protected by the access token
* Static health­check endpoint responding with JSON `{ “status”: “ok” }`
* Java8
* Relational database of your choice
* Store record IDs as UUIDs
* Git / mercurial repository (Bitbucket / Github)
* documentation (README) should be provided on how to:
* Install the application
* Run the application locally
* Run automatic tests
* Use API endpoint(s) (no need to be 100% complete)
* Deploy on free cloud service (i.e. Heroku)

## Installation
Requirements:
* Java 8
* MySQL 5.7+

Firstly, prepare DB
```$sql
create user 'moviedb'@'localhost' identified by 'moviedb';
create database moviedb;
grant all on moviedb.* to 'moviedb'@'localhost';
```
Then build the app (with tests)
```./gradlew clean build```

Finally, run the app
```java -jar build/libs/moviedb-0.0.1-SNAPSHOT.jar```

## API Docs
API can be reviewed using Swagger at `http://localhost:8080/swagger-ui.html`
or `https://spring-moviedb.herokuapp.com/swagger-ui.html`

## Deployment
Application was deployed on Heroku at `https://spring-moviedb.herokuapp.com` 

## Notes
#### What's with the package private?
The reason is domain separation. The inspiration was [this video](https://www.youtube.com/watch?v=5Q8kiSN6390). Facades are missing and I used `@Component` because I felt that it would be overkill in such context.

#### Where are the unit tests?
There are almost no business logic here therefore there are no unit tests. App is covered (95% lines) by integration tests instead, that are checking the app from the controller through the DB. Tests are transactional (rollback after the each test) which helps to maintain DB changes.

#### JWT support
JWT support without OAuth2 is terrible. There is even [this issue.](https://github.com/spring-projects/spring-security-oauth/issues/368)
I did not pick up OAuth2 because standard implementations assumes that you have separate server and client and I really want to build it as a single app.
 
JWT is actually great, because it removes state (session) from the app so you can run multiple instances without sticky session. 