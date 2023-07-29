plugins {
    id("platzi.android.application")
    id("platzi.android.compose")
    id("platzi.android.jacoco")
    id("platzi.android.hilt")
}

android {
    namespace = "com.platzi.randomcats"

    defaultConfig {
        applicationId = "com.platzi.randomcats"
        buildConfigField("String", "CATS_URL", "\"https://api.thecatapi.com\"")
        buildConfigField("String", "CATS_VERSION", "\"v1\"")
        buildConfigField("String", "CATS_KEY", "\"live_mtICrfeaEKViMcZiRxSaFxFAZk9jxri1FB71pALNy1USFrR97qmDjLMTReFiEG0y\"")
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

    implementation(libs.paging.compose)
    implementation(libs.hilt.ext.navigation)

    implementation(project(":core:network"))
    implementation(project(":feature-catshome"))

}