FROM openjdk:11
ARG JAR_FILE=target/restaurant-recommendation.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]
EXPOSE 8080


