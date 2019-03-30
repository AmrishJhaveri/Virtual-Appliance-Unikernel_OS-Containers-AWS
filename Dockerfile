FROM openjdk:8-jre-alpine
EXPOSE 8081
ADD /target/chess-rest-api-0.0.1-SNAPSHOT.jar chess-rest-api.jar
ENTRYPOINT ["java","-jar","chess-rest-api.jar"]
