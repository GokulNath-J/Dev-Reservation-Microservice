FROM openjdk:17-jdk
WORKDIR /indian-microservice
COPY /target/Indian_Railway-0.0.1-SNAPSHOT.jar  /indian-microservice/indian-railway-container.jar
EXPOSE 8882
CMD ["java","-jar","indian-railway-container.jar"]