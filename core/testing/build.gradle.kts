plugins {
    id("platzi.android.library")
    id("platzi.library.jacoco")
}

android {
    namespace = "com.platzi.randomcats.core.testing"

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {

    // Testing
    api(libs.junit)

    // Android Testing
    api(libs.androidx.test.ext)
    api(libs.androidx.test.espresso)
}