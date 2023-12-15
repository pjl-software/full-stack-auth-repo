.DEFAULT_GOAL := build-and-run-docker

# Some `make` shortcut commands
# For more options, run these bin commands directly from your terminal.
# Adding the -h option after each print the help() function

build-local:
	./bin/startup-scripts/build

build-docker:
	./bin/docker/build-and-extract-jar

build-and-run-local:
	./bin/startup-scripts/build-and-run

build-and-run-docker:
	./bin/docker/build-and-run-docker

generate-ssl-cert:
	./bin/ssl/enable-localhost-https

help:
	@echo "\nCheck the ./bin directory for utility scripts. Each one will have a [-h] option with help in addition to the code itself.\n" | fmt

run-local:
	./bin/startup-scripts/run

run-docker:
	./bin/docker/run-as-docker-container

