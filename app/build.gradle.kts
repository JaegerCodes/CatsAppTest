plugins {
    id("platzi.android.application")
    id("platzi.android.compose")
}

android {
    namespace = "com.platzi.randomcats"

    defaultConfig {
        applicationId = "com.platzi.randomcats"
        buildConfigField("String", "CATS_URL", "\"https://api.thecatapi.com/v1/\"")
        buildConfigField("String", "CATS_API_KEY", "\"live_mtICrfeaEKViMcZiRxSaFxFAZk9jxri1FB71pALNy1USFrR97qmDjLMTReFiEG0y\"")
    }

    buildTypes {
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

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)

    implementation(libs.hilt.android)

    implementation(project(":core:network"))
}