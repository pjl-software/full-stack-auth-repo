# Full Stack Authentication Repo

This is will eventually be a fully fledged user authentication application built with a Spring Boot (3.2+) back-end,
Angular front-end, and PostgreSQL datastore that will run on Docker. The repo will be runnable with a few key strokes
and with minimal local requirements.

## To Get Started

- Open a terminal
- `git clone https://github.com/pjl-software/full-stack-auth-repo.git`
- `cd` into this repo
- run `make build-and-run-local` or `make build-and-run-docker`

`make` will build and run the application for you automatically. If you run it locally, it will run a check that you have [Java 17](https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/macos-install.html) installed locally on your computer. If you run it via Docker, you'll need [Docker](https://docs.docker.com/get-docker/) running locally (Enginer 19.03.0+).

In addition to the `make` commands in the `Makefile`,
the repo has a number of utility scripts in the `bin` directories located within this repo. Each script has a `-h` help
option that will provide usage and documentation.

## Code Review Branch Videos

- **00-spring-boot-setup-local** [Branch](https://github.com/pjl-software/full-stack-auth-repo/tree/00-spring-boot-setup-local) and [Code Review Video](https://youtu.be/b2kl8cu3tC8?si=anWga882uFXQ6MJC)
- **01-run-server-local-on-https** [Branch](https://github.com/pjl-software/full-stack-auth-repo/tree/01-run-server-local-on-https) and [Code Review Video](https://youtu.be/qxVWOGYYFV0)
- **02-run-server-on-docker** [Branch](https://github.com/pjl-software/full-stack-auth-repo/tree/02-run-server-on-docker) and [Code Review Video](https://youtu.be/ks2qhwnO-a4)

## Why Are you Building This?

I don't like tutorials or reading raw documentation. What _I_ want when I'm learning something new, is a working
replica that I can tinker with to learn and build upon through trial and error. My goal is to provide this to
whoever learns like me in hopes that it helps them build some incredible stuff.

## How Are you Building This?

I'm starting small by breaking up logical chunks of the total effort into standalone branches that you can use
to follow along as the codebase builds up in complexity. If you don't care, no problem! Just checkout the `master`
branch and get it running. If you are looking for more context however, you can checkout the different branches
and watch the [Code Review walkthrough videos](https://www.youtube.com/playlist?list=PL2yILnfj7oN5Zggsb8latQequI1PJnqzn) I paired with them explaining the code and reasoning.
