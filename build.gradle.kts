plugins {
    id("java")
    id("org.springframework.boot") version "3.3.1"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}
group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    maven("https://maven-central-asia.storage-download.googleapis.com/maven2/")
    maven("https://repo.papermc.io/repository/maven-public/")
    mavenCentral()
}
dependencies {
    implementation("io.javalin:javalin:6.1.3")
    implementation("org.springframework:spring-core:6.1.10")
    implementation("org.aspectj:aspectjrt:1.9.22.1")
    implementation("org.aspectj:aspectjweaver:1.9.22.1")
    implementation ("org.apache.httpcomponents.client5:httpclient5:5.3.1")
    implementation ("org.slf4j:slf4j-api:2.0.13")
    implementation ("org.springframework:spring-context:6.1.10")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation ("org.junit.jupiter:junit-jupiter-api:5.10.3")
    compileOnly("io.papermc.paper:paper-api:1.18.2-R0.1-SNAPSHOT")
}

tasks.test {
    useJUnitPlatform()
}
java{
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}