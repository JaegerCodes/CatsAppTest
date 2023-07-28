plugins {
    id("platzi.android.application")
    id("platzi.android.compose")
    id("platzi.android.jacoco")
}

android {
    namespace = "com.platzi.randomcats"

    defaultConfig {
        applicationId = "com.platzi.randomcats"
    }

    buildTypes {
        debug {
            enableUnitTestCoverage = true
        }
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycleRuntime.ktx)

    implementation(libs.compose.activity)
    implementation(libs.compose.navigation)

    implementation(project(":feature-catshome"))
}