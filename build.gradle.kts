buildscript {
    dependencies {

    }
}
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.hilt) apply false
    id("io.gitlab.arturbosch.detekt") version "1.22.0"
    id("platzi.release.notes")
    id("platzi.detekt")
}