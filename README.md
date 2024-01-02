# Full Stack Authentication Repo

This codebase is a full-stack web application monorepo. It consists of a Spring Boot 3 back-end API with an Angular 17+ front-end. You can clone this repo, start the back-end and front-end, and begin tinkering with the code to understand how a full-stack web app works.

This is will eventually be a fully fledged user authentication application built with a Spring Boot (3.2+) back-end,
Angular front-end, and PostgreSQL datastore that will run on Docker. The repo will be runnable with a few key strokes
and with minimal local requirements.

## To Get Started

This repo has a number of utility scripts in the `bin` directories located within this repo. Each script has a `-h` help
option that will provide usage and documentation. We recommend using these scripts as they have more help and options than `make`.

### Using Custom /bin Scripts

This mono-repo contains both the front-end and back-end code for the full-stack application. Although you can run both processes from one terminal by running at least one in the background, **we recommend running the back-end and front-end seperately in their own terminal to keep things clear.** The `angular-front-end` and `spring-back-end` directories each have their own `./bin` directory with scripts to get the code running. Note that you may need to run `$ ./bin/ssl/enable-localhost-https` OR `make generate-ssl-cert` from the top-level of this repo to ensure SSL will work before you start the front and back-end.

#### spring-back-end

The Spring Boot back-end server codebase. The README.md file in this repo has instructions on getting it started. In general, you'd do something like the following:

1. Open a new terminal and `cd` into this repo
2. Run the spring-back-end:

- Locally
  - `$ ./spring-back-end/bin/local-startup-scripts/build-and-run` OR `cd spring-back-end && make build-and-run-local`
- Docker
  - `$ ./spring-back-end/bin/docker-startup-scripts/build-and-run-docker` OR `cd spring-back-end && make build-and-run-docker`

#### angular-front-end

The Anglar front-end server codebase. The README.md file in this repo has instructions on getting it started. In general, you'd do something like the following:

1. Open a new terminal and `cd` into this repo
2. Run the spring-back-end:

- Locally
  - `$ ./angular-front-end/local-startup-scripts/run` OR `cd angular-front-end && make run-local`

### Using Make

- Open a terminal
- `git clone https://github.com/pjl-software/full-stack-auth-repo.git`
- `cd` into this repo
- run `make build-and-run-local` (front-end and back-end) or `make build-and-run-docker` (back-end only)

## Code Review Branch Videos

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
