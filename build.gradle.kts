buildscript {
    dependencies {

    }
}
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    id("io.gitlab.arturbosch.detekt") version "1.22.0"
    id("org.jetbrains.kotlin.jvm") version "1.7.20" apply false
    id("platzi.release.notes")
    id("platzi.detekt")
}