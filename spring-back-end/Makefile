.DEFAULT_GOAL := build-and-run-docker

# Some `make` shortcut commands
# For more options, run these bin commands directly from your terminal.
# Adding the -h option after each print the help() function

LOCAL_START_UP_DIR := bin/local-startup-scripts
DOCKER_START_UP_DIR := bin/docker-startup-scripts

build-local:
	./${LOCAL_START_UP_DIR}/build

build-docker:
	./${DOCKER_START_UP_DIR}/build-and-extract-jar

build-and-run-local:
	./${LOCAL_START_UP_DIR}/build-and-run

build-and-run-docker:
	./${DOCKER_START_UP_DIR}/build-and-run-docker

help:
	@echo "\nCheck the ./bin directory for utility scripts. Each one will have a [-h] option with help in addition to the code itself.\n" | fmt

run-local:
	./${LOCAL_START_UP_DIR}/run

run-docker:
	./${DOCKER_START_UP_DIR}/run-as-docker-container

