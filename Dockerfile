FROM --platform=linux/amd64 gradle:8.2.1-jdk17-alpine
COPY ./build/libs/**SNAPSHOT.jar /app.jar
EXPOSE 8080
CMD ["java","-jar","/app.jar"]