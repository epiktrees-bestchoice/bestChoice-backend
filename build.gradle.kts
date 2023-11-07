import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
	id("java")
	id("org.springframework.boot") version "3.1.3"
	id("io.spring.dependency-management") version "1.1.3"
	kotlin("jvm") version "1.8.22"
	kotlin("plugin.spring") version "1.8.22"
	kotlin("plugin.jpa") version "1.8.22"
}

group = "bestChoice-backend"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	// spring
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	// https://mvnrepository.com/artifact/org.projectlombok/lombok
	implementation("org.projectlombok:lombok:1.18.28")
	annotationProcessor("org.projectlombok:lombok") // lombok 플러그
	// test 환경
	testImplementation("org.projectlombok:lombok")
	testAnnotationProcessor("org.projectlombok:lombok")

	// DB
	runtimeOnly("com.mysql:mysql-connector-j")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	//swagger 적용할 때, springfox 안 돼여,,,springdoc 써야함
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.2")
	// 스프링 시큐리티 버전
	implementation("org.springframework.boot:spring-boot-starter-security:3.1.3")
	implementation("org.springframework.security:spring-security-test:6.1.3")
	implementation("org.springframework.boot:spring-boot-starter-oauth2-client:3.1.3")
	// tymelead
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	// google OAuth libary
	implementation("com.google.api-client:google-api-client:1.32.1")

	//Querydsl 추가
	implementation ("com.querydsl:querydsl-jpa:5.0.0:jakarta")
	annotationProcessor ("com.querydsl:querydsl-apt:5.0.0:jakarta")
	annotationProcessor ("jakarta.annotation:jakarta.annotation-api")
	annotationProcessor ("jakarta.persistence:jakarta.persistence-api")
	// 쿼리 파라미터를 로그로 남기는 외부 라이브러리
	implementation ("com.github.gavlyukovskiy:p6spy-spring-boot-starter:1.9.0")
}


tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
