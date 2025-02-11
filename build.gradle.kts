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

val spigotVersion = "1.20.4-R0.1-SNAPSHOT"
val slimefunVersion = "RC-37"
val slimefunTranslationVersion = "e03b01a7b7"
val guizhanLibVersion = "1.7.6"
val bstatsVersion = "3.0.2"

dependencies {
    compileOnly("org.spigotmc:spigot-api:${spigotVersion}")
    compileOnly("com.github.Slimefun:Slimefun4:${slimefunVersion}")
    compileOnly("net.guizhanss:SlimefunTranslation:${slimefunTranslationVersion}")
    implementation("net.guizhanss:GuizhanLib-api:${guizhanLibVersion}")
    implementation("org.bstats:bstats-bukkit:${bstatsVersion}")
}

kotlin {
    jvmToolchain(17)
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
    apiVersion = "1.16"
    authors = listOf("ybw0014")
    description = "A Slimefun Addon that adds various types of generators."
    depend = listOf("Slimefun")
    softDepend = listOf("GuizhanLibPlugin", "SlimefunTranslation")
}
