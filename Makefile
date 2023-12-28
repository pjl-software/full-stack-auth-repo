.DEFAULT_GOAL := build-and-run-docker

SPRING_BACK_END_DIR := spring-back-end
ANGULAR_FRONT_END_DIR := angular-front-end


# Some `make` shortcut commands
# For more options, run these bin commands directly from your terminal.
# Adding the -h option after each print the help() function

build-and-run-local:
	@echo "This is a compound make command that will start the back-end server in the background and then start the front-end server. This can be confusing because you are running to processes from a single terminal. I'd recommend opening two terminal tabs/windows and starting the back-end server in one, and the front-end application in another." | fmt
	@echo
	@echo "Starting the Spring Boot back-end server" | fmt
	cd ./${SPRING_BACK_END_DIR} && $(MAKE) build-and-run-local &
	cd ..
	@echo "Starting the Angular front-end application. ctrl-c should quit both." | fmt
	cd ./${ANGULAR_FRONT_END_DIR} && $(MAKE) run-local

build-and-run-docker: # For now this is just the back-end, will be front-end too once built
	cd ./${SPRING_BACK_END_DIR} && $(MAKE) build-and-run-docker

generate-ssl-cert:
	./bin/ssl/enable-localhost-https