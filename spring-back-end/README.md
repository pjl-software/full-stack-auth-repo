# Spring Boot Back-end

## Overview

This codebase represents a **complete** Spring Boot (3.2) back-end that will run on your local
machine out of the box. This branch has a health check endpoint that can be reached by navigating
to https://localhost:8443/health. This demo is intended to help give you a starting point with Spring Boot
to tinker and expand while learning the tech.

[Code Review Video](https://youtu.be/b2kl8cu3tC8?si=anWga882uFXQ6MJC)

### Getting Started

1. Clone this repo to your local machine

- `$ git clone https://github.com/pjl-software/full-stack-auth-repo.git`

2. Change directories into the codebase

- `$ cd full-stack-auth-repo`

3. Make sure you've created your SSL Cert and followed the instructions to run the app on HTTPS

- `$ ./bin/ssl/enable-localhost-https` OR `make generate-ssl-cert`

3. Run the application

- Locally
  - `$ ./spring-back-end/bin/local-startup-scripts/build-and-run` OR `cd spring-back-end && make build-and-run-local`
- Docker
  - `$ ./spring-back-end/bin/docker-startup-scripts/build-and-run-docker` OR `cd spring-back-end && make build-and-run-docker`

And Voilà! You have a back-end server up and running on HTTPS.

### Test The Running Application

Open a browers and navigate to https://localhost:8443/health

## Start the Front-End

Open a separate terminal and follow the steps in the angular-front-end/README.md

### Assumptions

- If you want to run on Docker:
  - You need to install [Docker](https://docs.docker.com/engine/install/)
- If you want to run locally:
  - I'm developing this on a Mac, and that's the only OS I'm testing it on. I'm confident it will run on a Linux
    distribution.
  - I'm also
    using [Amazon Corretto 17](https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/what-is-corretto-17.html)

### Issues

If you experience issues, and things just aren't running for you, let me know by opening an issue.
