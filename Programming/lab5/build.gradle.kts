import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.util.archivesName

plugins {
    id("java")
    kotlin("jvm")
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "itmo.programming"
version = "1.0-SNAPSHOT"


repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation(kotlin("stdlib-jdk8"))
}
tasks.jar {
    manifest{
        attributes["Main-class"] = "itmo.programming.Main"
    }
}
tasks.test {
    useJUnitPlatform()
}

tasks.shadowJar{
    archiveBaseName.set("lab5")
    archiveClassifier.set("")
    archiveVersion.set("")
}
kotlin {
    jvmToolchain(21)
}