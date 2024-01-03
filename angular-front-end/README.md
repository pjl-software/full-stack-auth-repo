# Angular Front-End

## Overview

This codebase represents a **complete** Angular (17) front-end that will run on your local
machine out of the box. This branch has a simple application that is integrated with the Spring Boot back-end (`spring-back-end`) that is also part of this repo. When running, this app can be reached at `https://localhost:4200`.

This demo is intended to help give you a starting point with Angular and a real back-end
to tinker and expand while learning the tech.

### Getting Started

1. Clone this repo to your local machine

- `$ git clone https://github.com/pjl-software/full-stack-auth-repo.git`

2. Change directories into the codebase

- `$ cd full-stack-auth-repo`

3. Make sure you've created your SSL Cert and followed the instructions to run the app on HTTPS

- `$ ./bin/ssl/enable-localhost-https` OR `make generate-ssl-cert`

4. Run the application

- Locally
  - `$ ./angular-front-end/local-startup-scripts/run` OR `cd angular-front-end && make run-local`
- Docker
  - `$ ./angular-front-end/docker-startup-scripts/run-as-docker-container` OR `cd angular-front-end && make run-docker`

And Voilà! You have an Angular front-end single page application running locally on HTTPS.

### Test The Running Application

Open a browers and navigate to https://localhost:4200

#### Start the Back-End

Open a separate terminal and follow the steps in the spring-back-end/README.md

### Issues

If you experience issues, and things just aren't running for you, let me know by opening an issue.
