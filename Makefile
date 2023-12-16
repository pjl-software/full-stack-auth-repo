.DEFAULT_GOAL := build-and-run-docker

BACK_END_DIR := spring-back-end

# Some `make` shortcut commands
# For more options, run these bin commands directly from your terminal.
# Adding the -h option after each print the help() function

build-and-run-local: # For now this is just the back-end, will be front-end too once built
	cd ./${BACK_END_DIR} && $(MAKE) build-and-run-local

build-and-run-docker: # For now this is just the back-end, will be front-end too once built
	cd ./${BACK_END_DIR} && $(MAKE) build-and-run-docker