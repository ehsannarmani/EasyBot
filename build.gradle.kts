val kotlin_version: String by project
val logback_version: String by project

plugins {
    application
    kotlin("jvm") version "1.7.0"
    id("io.ktor.plugin") version "2.1.2"
    kotlin("plugin.serialization") version "1.7.0"

}

group = "narmani.ehsan"
version = "1.0.0"
application {
    mainClass.set("com.github.ehsannarmani.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")

}

repositories {
    mavenCentral()
}
tasks {
    create("stage").dependsOn("installDist")
}

dependencies {

    implementation("ch.qos.logback:logback-classic:$logback_version")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")

    implementation(project("bot"))


}