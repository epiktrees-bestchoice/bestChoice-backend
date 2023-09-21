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
//	runtimeOnly("com.h2database:h2")
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
