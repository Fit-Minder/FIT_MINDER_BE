FROM openjdk:17-alpine
VOLUME /tmp
COPY /build/libs/fitMinder-0.0.1-SNAPSHOT-plain.jar fit.jar
ENTRYPOINT ["java","-jar","fit.jar"]
EXPOSE 8080