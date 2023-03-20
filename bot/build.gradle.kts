val ktor_version: String by project
val koin_version: String by project
val koin_ktor: String by project
val kotlin_version: String by project
val logback_version: String by project

plugins {
    kotlin("jvm") version "1.7.0"
    id("io.ktor.plugin") version "2.1.2"
    kotlin("plugin.serialization") version "1.7.0"
    `maven-publish`
}
publishing {
    publications {
        create("maven", MavenPublication::class) {
            afterEvaluate {
                from(components["java"])
            }
        }
    }
}
application {
    mainClass.set("com.github.ehsannarmani.bot.Bot")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")

}
repositories {
    maven("https://jitpack.io")
    mavenCentral()
}


dependencies {
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-cio:$ktor_version")
    implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
    implementation("io.ktor:ktor-client-logging:$ktor_version")
    api("io.insert-koin:koin-core:$koin_version")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
    implementation("com.fasterxml.jackson.core:jackson-core:2.14.2")
}