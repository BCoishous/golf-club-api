# Golf Club API

A Spring Boot REST API for managing golf club memberships and tournaments.
Connects to a PostgreSQL database hosted on AWS RDS and runs in Docker.

## How to Run in Docker

Make sure Docker Desktop is running, then:

git clone https://github.com/BCoishous/golf-club-api.git
cd golf-club-api
docker-compose up --build

The API will be available at http://localhost:8080

## API Endpoints

### Members

- POST /api/members — add a new member
- GET /api/members — get all members
- GET /api/members/{id} — get member by ID
- GET /api/members/search/name?name= — search by name
- GET /api/members/search/type?type= — search by membership type
- GET /api/members/search/phone?phone= — search by phone number
- GET /api/members/search/tournament-date?date= — search by tournament date

### Tournaments

- POST /api/tournaments — add a new tournament
- GET /api/tournaments — get all tournaments
- GET /api/tournaments/{id} — get tournament by ID
- GET /api/tournaments/search/date?date= — search by start date
- GET /api/tournaments/search/location?location= — search by location
- POST /api/tournaments/{tournamentId}/members/{memberId} — register a member to a tournament

---

## AWS RDS

The API connects to a PostgreSQL instance running on AWS RDS.
The connection string is set via the SPRING_DATASOURCE_URL environment variable
in docker-compose.yml so credentials stay out of the codebase.

Hibernate handles table creation automatically on startup using ddl-auto=update
so no manual SQL setup is needed.

## CI/CD

A GitHub Action in .github/workflows/docker.yml builds and pushes the Docker
image to Docker Hub on every merge to main. The image is available at
bcoishous/golf-club-api:latest

## Issues I ran into

The biggest challenge was the many to many relationship between members and tournaments causing an infinite loop in the JSON response. Fixed it by adding @JsonIgnore to the tournaments field in the Member class so the serializer knows where to stop.

Setting up the Docker token for GitHub Actions took a few tries, the token needs Read, Write and Delete permissions or the push step fails with an insufficient scopes error.
