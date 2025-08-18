plugins {
    kotlin("jvm") version "2.1.10"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("net.minecrell.plugin-yml.bukkit") version "0.6.0"
}

group = "net.guizhanss"
version = "UNOFFICIAL"
description = "UltimateGenerators2"

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
    maven("https://jitpack.io")
}

val spigotVersion = "1.21.1-R0.1-SNAPSHOT"
val slimefunVersion = "master-SNAPSHOT"
val slimefunTranslationVersion = "e03b01a7b7"
val guizhanLibVersion = "2.3.0"
val bstatsVersion = "3.1.0"

dependencies {
    implementation("org.spigotmc:spigot-api:${spigotVersion}")
    implementation("com.github.SlimefunGuguProject:Slimefun4:${slimefunVersion}")
    implementation("net.guizhanss:SlimefunTranslation:${slimefunTranslationVersion}")
    implementation("net.guizhanss:GuizhanLibPlugin:${guizhanLibVersion}")
    implementation("org.bstats:bstats-bukkit:${bstatsVersion}")
}

kotlin {
    jvmToolchain(21)
}

tasks.compileKotlin {
    kotlinOptions.javaParameters = true
}

tasks.shadowJar {
    fun doRelocate(from: String) {
        val last = from.split(".").last()
        relocate(from, "net.guizhanss.ultimategenerators2.libs.$last")
    }
    doRelocate("net.guizhanss.guizhanlib")
    doRelocate("org.bstats")
    minimize()
    archiveClassifier = ""
}

bukkit {
    main = "net.guizhanss.ultimategenerators2.UltimateGenerators2"
    apiVersion = "1.21"
    authors = listOf("ybw0014")
    description = "A Slimefun Addon that adds various types of generators."
    depend = listOf("Slimefun")
    softDepend = listOf("GuizhanLibPlugin", "SlimefunTranslation")
}
