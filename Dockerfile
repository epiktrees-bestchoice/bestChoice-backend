FROM --platform=linux/amd64 gradle:8.2.1-jdk17-alpine

# gradle 복사
COPY build.gradle.kts .
COPY settings.gradle.kts .

# 프로젝트 소스코드 복사
COPY gradle gradle
COPY src/main/java src/main/java
COPY src/main/resources src/main/resources

ENV SPRING_DATASOURCE_URL: jdbc:mysql://mysqldb:3306/myseconddb
ENV SPRING_JPA_PROPERTIES_HIBERNATE_DIALECT: org.hibernate.dialect.MySQL8Dialect
ENV MYSQL_HOST: mysqldb
ENV MYSQL_PORT: 3306
ENV MYSQL_USER: myspringboot
ENV MYSQL_PASSWORD: 3219
ENV MYSQL_DB: myseconddb

# 실행 가능한 jar파일  이미지에 생성
RUN gradle build -x test

# ENTRYPOINT는 이미지를 컨테이너로 띄울 때 항상 실행되야하는 커멘드를 지정
ENTRYPOINT ["java","-jar","./build/libs/spring-0.0.1-SNAPSHOT.jar"]