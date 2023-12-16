# Do not run this Dockerfile directly
# Instead, leverage:
# make TBD
# ./bin/docker TBD

FROM openjdk:17-alpine

# Brute forece way
#COPY pjl-application/pjl-application-core/target/pjl-application-core.jar ./
#ENTRYPOINT ["java", "-jar", "pjl-application-core.jar"]

RUN addgroup -S demo && adduser -S demo -G demo
USER demo

ARG EXTRACTED=pjl-application/pjl-application-core/target/extracted

COPY ${EXTRACTED}/dependencies/ ./
COPY ${EXTRACTED}/spring-boot-loader/ ./
COPY ${EXTRACTED}/snapshot-dependencies/ ./
COPY ${EXTRACTED}/application/ ./

ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]