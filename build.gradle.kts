plugins {
    java
    id("com.github.dcendents.android-maven")
}

group = "com.github.thoo0224"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
}

dependencies {
    implementation("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT")
    implementation("org.bukkit:bukkit:1.8.8-R0.1-SNAPSHOT")
}

buildscript {
    dependencies {
        classpath("com.github.dcendents:android-maven-gradle-plugin:2.0")
    }
}
