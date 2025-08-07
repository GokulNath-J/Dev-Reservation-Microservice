FROM openjdk:17-jdk
WORKDIR /passenger-reservation
COPY /target/Passenger-Reservation-0.0.1-SNAPSHOT.jar /passenger-reservation/passenger-reservation.jar
EXPOSE 8881
CMD ["java","-jar","passenger-reservation.jar"]