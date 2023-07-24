plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
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

        register("androidLibraryCompose") {
            id = "platzi.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }

        register("androidLibrary") {
            id = "platzi.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        register("androidFeature") {
            id = "platzi.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }

        register("androidApplicationJacoco") {
            id = "platzi.android.jacoco"
            implementationClass = "AndroidApplicationJacocoConventionPlugin"
        }

        register("androidLibraryJacoco") {
            id = "platzi.library.jacoco"
            implementationClass = "AndroidLibraryJacocoConventionPlugin"
        }

        register("androidLibraryHilt") {
            id = "platzi.library.hilt"
            implementationClass = "AndroidLibraryHiltConventionPlugin"
        }
    }
}