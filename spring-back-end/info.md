# Spring Boot Back-end

## Overview

This codebase represents a **complete** codebase for setting up a Spring Boot server that will run on your local
machine. The idea is that you can `git clone` this repo locally, run the `./bin` shortcuts, and get the code
up and running on your machine to tinker and build.

My goal is to accompany each branch with a "PR Walkthrough" video that I will post online where I go through
the files and changes I've made, and why I made them. I'd like to pair context with code that runs rather than
write a tutorial. In my experience, (well written) code is the best documentation, so I will try my best to do that.

## Quick Assumptions

- I'm developing this on a Mac, and that's the only OS I'm really testing it on. I'm confident it will run on Linux as
  well.
- I'm also
  using [Amazon Corretto 17](https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/what-is-corretto-17.html)

If you experience issues, and things just aren't running for you, let me know by opening an issue.

## Getting Started

- Clone this repo: `git clone https://github.com/pjlinn/spring-angular-auth-repo.git`
- `master` will be the latest, most up-to-date branch. However, I'll be timestamping branches that I pair with PR
  Walkthrough videos, so you can check out those branches to see the repo in a different state. This can be helpful if
  you want to know why code is a certain way or a file exists.
- There are `bin` executable scripts I've written as shortcuts for doing things in the repo (similar to a Makefile)
    - For example, if you wanted to build and run the spring-boot server locally, you could
      run: `./spring-back-end/bin/startup-scripts/build-and-run`
