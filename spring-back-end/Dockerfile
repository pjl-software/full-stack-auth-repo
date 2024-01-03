# Do not run this Dockerfile directly
# Instead, leverage:
# $ make build-jar-and-run-docker
# $ ./bin/docker-startup-scripts/build-jar-and-run-docker

FROM amazoncorretto:17-alpine-jdk

RUN addgroup -S demo && adduser -S demo -G demo
USER demo

ARG EXTRACTED=pjl-application/pjl-application-core/target/extracted

COPY ${EXTRACTED}/dependencies/ ./
COPY ${EXTRACTED}/spring-boot-loader/ ./
COPY ${EXTRACTED}/snapshot-dependencies/ ./
COPY ${EXTRACTED}/application/ ./

ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]

# Brute forece way
#COPY pjl-application/pjl-application-core/target/pjl-application-core.jar ./
#ENTRYPOINT ["java", "-jar", "pjl-application-core.jar"]