plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.detekt.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "platzi.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        register("androidApplicationCompose") {
            id = "platzi.android.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }

        register("androidLibrary") {
            id = "platzi.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        register("androidApplicationJacoco") {
            id = "platzi.android.jacoco"
            implementationClass = "AndroidApplicationJacocoConventionPlugin"
        }

        register("androidLibraryJacoco") {
            id = "platzi.library.jacoco"
            implementationClass = "AndroidLibraryJacocoConventionPlugin"
        }

        register("releaseNotes") {
            id  = "platzi.release.notes"
            implementationClass = "ReleaseNotesConventionPlugin"
        }

        register("detekt") {
            id = "platzi.detekt"
            implementationClass = "DetektConventionPlugin"
        }
    }
}