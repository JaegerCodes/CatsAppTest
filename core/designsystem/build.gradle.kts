plugins {
    id("platzi.android.library")
    id("platzi.library.compose")
    id("platzi.library.jacoco")
}

android {
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    lint {
        checkDependencies = true
    }
    namespace = "com.platzi.randomcats.core.designsystem"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    androidTestImplementation(project(":core:testing"))
}