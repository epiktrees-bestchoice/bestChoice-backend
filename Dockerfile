FROM --platform=linux/amd64 gradle:8.2.1-jdk17-alpine
ARG JAR_FILE=./build/libs/*SNAPSHOT.jar
COPY ${JAR_FILE} /app.jar
EXPOSE 8080
CMD ["java","-jar","/app.jar"]