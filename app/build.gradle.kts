plugins {
    id("se.patrikerdes.use-latest-versions") version "0.2.19"
    id("com.github.ben-manes.versions") version "0.53.0"
    id("org.sonarqube") version "7.1.0.6387"
    java
    checkstyle
    jacoco
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

checkstyle {
    toolVersion = "10.12.4"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}
tasks.jacocoTestReport { reports { xml.required.set(true) } }

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

sonar {
    properties {
        property("sonar.projectKey", "artmper_java-project-78")
        property("sonar.organization", "artmper")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}
