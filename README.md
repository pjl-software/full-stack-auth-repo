# Full Stack Authentication Repo

This codebase is a full-stack web application monorepo. It consists of a Spring Boot 3 back-end API with an Angular 17+ front-end. You can clone this repo, start the back-end and front-end, and begin tinkering with the code to understand how a full-stack web app works.

This is will eventually be a fully fledged user authentication application built with a Spring Boot (3.2+) back-end,
Angular front-end, and PostgreSQL datastore that will run on Docker. The repo will be runnable with a few key strokes
and with minimal local requirements.

Please note, however, that **this code is intended for educational purposes only. It is not running in production, nor is it claiming to be "production-ready" code**. The code is simply intended to teach and allow you to learn frameworks through practical usage.

## To Get Started

This repo has a number of utility scripts in the `bin` directories located within this repo. Each script has a `-h` help
option that will provide usage and documentation. We recommend using these scripts as they have more help and options than `make`.

### Using Custom /bin Scripts

This mono-repo contains both the front-end and back-end code for the full-stack application. Although you can run both processes from one terminal by running at least one in the background, **we recommend running the back-end and front-end seperately in their own terminal to keep things clear.** The `angular-front-end` and `spring-back-end` directories each have their own `./bin` directory with scripts to get the code running. Note that you may need to run `$ ./bin/ssl/enable-localhost-https` OR `make generate-ssl-cert` from the top-level of this repo to ensure SSL will work before you start the front and back-end.

#### full stack app

1. Open a new terminal and `cd` into this repo

- Docker
  - `./bin/docker-startup-scripts/run-app-with-docker -b`

#### spring-back-end

The Spring Boot back-end server codebase. The README.md file in this repo has instructions on getting it started. In general, you'd do something like the following:

1. Open a new terminal and `cd` into this repo
2. Run the spring-back-end:

- Locally
  - `$ ./spring-back-end/bin/local-startup-scripts/build-and-run` OR `cd spring-back-end && make build-and-run-local`
- Docker
  - `$ ./spring-back-end/bin/docker-startup-scripts/build-jar-and-run-docker` OR `cd spring-back-end && make build-jar-and-run-docker`

#### angular-front-end

The Anglar front-end server codebase. The README.md file in this repo has instructions on getting it started. In general, you'd do something like the following:

1. Open a new terminal and `cd` into this repo
2. Run the spring-back-end:

- Locally
  - `$ ./angular-front-end/bin/local-startup-scripts/run` OR `cd angular-front-end && make run-local`
- Docker
  - `$ ./angular-front-end/bin/docker-startup-scripts/run-as-docker-container -b` OR `cd angular-front-end && make run-docker`

### Using Make to Run the Full-Stack App

- Open a terminal
- `git clone https://github.com/pjl-software/full-stack-auth-repo.git`
- `cd` into this repo
  - Locally:
    - $ `make build-and-run-local`
  - Docker:
    - $ `make build-and-run-docker`

## Code Review Branch Videos

### Part 1

In part 1, we set up our full-stack application with a Spring Boot back-end and an Angular front-end. We get both applications integrated with one another and dockerize the entire stack. This section sets the foundation as we start building out our user managment system.

- **00-spring-boot-setup-local**
  - [Branch](https://github.com/pjl-software/full-stack-auth-repo/tree/00-spring-boot-setup-local)
  - [Code Review Video](https://youtu.be/b2kl8cu3tC8?si=anWga882uFXQ6MJC)
  - Summary: Setting up our back-end server codebase using Spring Boot. Build a GET request health check as our first API.
- **01-run-server-local-on-https**
  - [Branch](https://github.com/pjl-software/full-stack-auth-repo/tree/01-run-server-local-on-https)
  - [Code Review Video](https://youtu.be/qxVWOGYYFV0)
  - Summary: Our API runs on HTTP, but we need it to run on HTTPS to be functional and mimic production. Here we enable SSL and generate the required files.
- **02-run-server-on-docker**
  - [Branch](https://github.com/pjl-software/full-stack-auth-repo/tree/02-run-server-on-docker)
  - [Code Review Video](https://youtu.be/ks2qhwnO-a4)
  - Summary: So far, our Spring Boot API runs locally on HTTPS, but what if your local environment differs and this codebase won't run for you? We set up our Spring Boot API on Docker so that anyone with Docker can run this code out-of-the-box over Docker.
- **03-mono-repo-management**
  - [Branch](https://github.com/pjl-software/full-stack-auth-repo/tree/03-mono-repo-management)
  - [Code Review Video](https://youtu.be/LH2ajoHEdg0)
  - We add an additional codebase to this repo for our Angular front-end application. This second codebase in our mono-repo required some refactoring remove redundancy and reuse code.
- **04-angular-spring-local-integration**
  - [Branch](https://github.com/pjl-software/full-stack-auth-repo/tree/04-angular-spring-local-integration)
  - [Code Review Video](https://youtu.be/D3C9SL-5cug?si=Xcq3o_gH_CW49HTk)
  - We refactor our Angular codebase to connect to our Spring Boot back-end health check API **creating a full stack web application**.
- **05-spring-actuator-integration**
  - [Branch](https://github.com/pjl-software/full-stack-auth-repo/tree/05-spring-actuator-integration)
  - [Code Review Video](https://youtu.be/EhpXXePOS9M)
  - We remove the health check API we created and replace it with the one Spring provides through the Spring Boot Starter Actuator library.
- **06-run-app-on-docker**
  - [Branch](https://github.com/pjl-software/full-stack-auth-repo/tree/06-run-app-on-docker)
  - [Code Review Video](https://youtu.be/mvV6jNWcrEg)
  - Use `docker compose` to dockerize the entire full-stack application. Update the `/bin` scripts and `makefiles` to start the Angular front-end, Spring Boot back-end, and then entire full-stack application with `docker`. Add `docker` utility scripts to manage the environment.

### Part 2

- **07-angular-repo-structure-overview**
  - [Branch](https://github.com/pjl-software/full-stack-auth-repo/tree/07-angular-repo-structure-overview)
  - [Code Review Video](https://youtu.be/UI92yhRM4wA)
  - Walk through the Angular repo structure and walkthrough examples of creating NgModules and components. Demonstrate how all the Angular components work together, and run through some of the most common errors and how to fix them.

#### Mini-Series: Data Layer Integration

Our full-stack application needs a data layer. In this mini-series, we'll be integrating PostgreSQL into our application locally using Docker.

- **08-db-integration-a**

  - [Branch](https://github.com/pjl-software/full-stack-auth-repo/tree/08-db-integration-a)
  - [Code Review Video](https://youtu.be/OGHNGp9KJLU)
  - Integrating the PostgreSQL Docker image into our existing `docker-compose.yaml` file and linking it to our back-end Spring Boot service. Making sure we can start the DB with the rest of the application and easily access the image.

- **08-db-integration-b**

  - [Branch](https://github.com/pjl-software/full-stack-auth-repo/tree/08-db-integration-b)
  - [Code Review Video](https://youtu.be/OvDmhvy6xC8)
  - In this branch, we configure the Spring Boot `DataSource` to connect to our running `PostgreSQL` Docker image, we use `JPA` to create a `users` table, and we create an API `POST` request that will generate and save new `User` entitie to our database.

- **08-db-integration-c**
  - [Branch](https://github.com/pjl-software/full-stack-auth-repo/tree/08-db-integration-c)
  - [Code Review Video](https://youtu.be/cJNjbB3slSw)
  - In this branch, we create an API for deleting a user and viewing enabled users; then Angular front-end components for interacting with each API. This branch will give you a interactive UI for managing users in the PostgreSQL DB.

#### Mini-Series: API/Spring Security Integration

To have fully functional user authentication and authorization, we need to secure our APIs and modify our front-end services/http requests accordingly. We will be using Spring Security to secure our APIs and Angular interceptors to adjust our HTTP requests.

- **09-spring-security-configuration-a**

  - [Branch](https://github.com/pjl-software/full-stack-auth-repo/tree/09-spring-security-configuration-a)
  - [Code Review Video](https://youtu.be/wphsd2RGPWc)
  - We need to take a quick detour to refactor our Angular application to use the latest features introduced in Angular 15. This will make integrating with Spring Security easier and future-proof our older Angular code against possible deprecation. We also stop returning JPA Entities in our APIs and use Projection DTOs instead.

- **09-spring-security-configuration-b**

  - [Branch](https://github.com/pjl-software/full-stack-auth-repo/tree/09-spring-security-configuration-b)
  - [Code Review Video](https://youtu.be/p4Fn-xlzNEQ)
  - We dip our toe into Spring Security with our first SecurityFilterChain configuring HTTP Basic authentication on our UserController APIs. We add an HTTP Interceptor to Angular to include an Authorization header with each request to keep our components functional.

- **09-spring-security-configuration-c**

  - [Branch](https://github.com/pjl-software/full-stack-auth-repo/tree/09-spring-security-configuration-c)
  - [Code Review Video](https://youtu.be/NX6u7SIHHAA)
  - We ditch HTTP Basic authentication for OAuth2 with JSON Web Tokens (JWT). We integrate our OAuth2 authentication with Angular using `angularx-social-login` to get valid JWTs using Google accounts with Google Sign-in. You will need a Google Provider ID from [https://console.cloud.google.com/](https://console.cloud.google.com/) > APIs and Services > Credentials > + Create Credentials > OAuth Client ID > Web Application > Create

- **09-spring-security-configuration-d**

  - [Branch](https://github.com/pjl-software/full-stack-auth-repo/tree/09-spring-security-configuration-d)
  - [Code Review Video](https://youtu.be/AlFIYek3B5s)
  - Proper user management requires role-based access. In this branch, we introduce a Role entity and roles table in our PostgreSQL DB with a many-to-many relationship between Users and Roles using JPA. We mature our business logic by creating a dedicate UserService interface and subsequent implementation to handle users logging in via Google Sign-on.

- **09-spring-security-configuration-e**

  - [Branch](https://github.com/pjl-software/full-stack-auth-repo/tree/09-spring-security-configuration-e)
  - [Code Review Video](https://youtu.be/VOXno44nCkA)
  - Enrich our JwtAuthenticationToken from Google with our managed GrantedAuthorities to use with the newly enabled @PreAuthorize annotation. Set up `@PreAuthorize("hasRole('ROLE_ADMIN')")` and `@PreAuthorize("hasRole('ROLE_FREE_USER')")` endpoints. Create a custom `@CurrentUser` annotation for loading the `User` entity associated with the valid JWT as the `@AuthenticationPrincipal`.

### Part 3 - Refining the Web App

- **10-improving-user-login-flow-a**

  - [Branch](https://github.com/pjl-software/full-stack-auth-repo/tree/10-improving-user-login-flow-a)
  - [Code Review Video](https://youtu.be/fF8BvbWG_Z8)
  - Enhance front-end view based on the user's state. Extend the back-end to return an enriched user info and leverage that user info to control the front-end view with Angular. Replace `JpaRepository` with `BaseJpaRepository`.

## Why Are you Building This?

I don't like tutorials or reading raw documentation. What _I_ want when I'm learning something new, is a working
replica that I can tinker with to learn and build upon through trial and error. My goal is to provide this to
whoever learns like me in hopes that it helps them build some incredible stuff.

I also find myself wanting more context than the code or tutorial provides, which is why I pair the code with video code reviews.

## How Are you Building This?

I'm starting small by breaking up logical chunks of the total effort into standalone branches that you can use
to follow along as the codebase builds up in complexity. If you don't care, no problem! Just checkout the `master`
branch and get it running. If you are looking for more context however, you can checkout the different branches
and watch the [Code Review walkthrough videos](https://www.youtube.com/playlist?list=PL2yILnfj7oN5Zggsb8latQequI1PJnqzn) I paired with them explaining the code and reasoning.
