FROM --platform=linux/amd64 gradle:8.2.1-jdk17-alpine

# gradle 복사
COPY build.gradle.kts .
COPY settings.gradle.kts .

# 프로젝트 소스코드 복사
COPY gradle gradle
COPY src/main/java src/main/java
COPY src/main/resources src/main/resources


# 실행 가능한 jar파일  이미지에 생성
RUN gradle build -x test

# ENTRYPOINT는 이미지를 컨테이너로 띄울 때 항상 실행되야하는 커멘드를 지정
ENTRYPOINT ["java","-jar","./build/libs/spring-0.0.1-SNAPSHOT.jar"]