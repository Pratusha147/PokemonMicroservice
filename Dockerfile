FROM openjdk:11
EXPOSE 8080
ADD target/pokemon-microservice-docker.jar pokemon-microservice-docker.jar
ENTRYPOINT ["java","-jar","/pokemon-microservice-docker.jar"]