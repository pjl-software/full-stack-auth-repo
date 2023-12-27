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

2. Change directories into this directory and checkout this branch (_01-run-server-local-on-https_)

- `$ cd full-stack-auth-repo/spring-back-end && git checkout 01-run-server-local-on-https`

3. Make sure you've created your SSL Cert and followed the instructions to run the app on HTTPS

- `$ ./bin/ssl/enable-localhost-https` OR `make generate-ssl-cert`

3. Run the application

- Locally
    - `$ ./bin/local-startup-scripts/build-and-run` OR `make build-and-run-local`
- Docker
    - `$ ./bin/docker-startup-scripts/build-and-run-docker` OR `make build-and-run-docker`

And Voilà! You have a back-end server up and running on HTTPS.

[//]: # '<img src="https://s3.amazonaws.com/htscodelookup.com/github/pjl-software/spring-angular-auth-repo/00-spring-boot-setup-local/getting-started-simple-720.gif" width="45%" height="45%"  alt="getting started running the api"/>'

### Test The Running Application

Open a browers and navigate to https://localhost:8443/health

[//]: # '<img src="https://s3.amazonaws.com/htscodelookup.com/github/pjl-software/spring-angular-auth-repo/00-spring-boot-setup-local/testing-the-api.gif" width="45%" height="45%"  alt="testing the api"/>'

### Assumptions

- I'm developing this on a Mac, and that's the only OS I'm testing it on. I'm confident it will run on a Linux
  distribution.
- I'm also
  using [Amazon Corretto 17](https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/what-is-corretto-17.html)

If you experience issues, and things just aren't running for you, let me know by opening an issue.
