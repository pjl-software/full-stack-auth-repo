.DEFAULT_GOAL := build-and-run-docker

BACK_END_DIR := spring-back-end

# Some `make` shortcut commands
# For more options, run these bin commands directly from your terminal.
# Adding the -h option after each print the help() function

build-back-end-local:
	cd ./${BACK_END_DIR} && $(MAKE) build-local

build-back-end-docker:
	cd ./${BACK_END_DIR} && $(MAKE) build-docker

build-and-run-local: # For now this is just the back-end, will be front-end too once built
	cd ./${BACK_END_DIR} && $(MAKE) build-and-run-local

build-and-run-docker: # For now this is just the back-end, will be front-end too once built
	cd ./${BACK_END_DIR} && $(MAKE) build-and-run-docker

build-and-run-back-end-local:
	cd ./${BACK_END_DIR} && $(MAKE) build-and-run

run-back-end-local:
	cd ./${BACK_END_DIR} && $(MAKE) run-local

run-back-end-docker:
	cd ./${BACK_END_DIR} && $(MAKE) run-docker