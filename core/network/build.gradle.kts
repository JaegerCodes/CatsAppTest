plugins {
    id("platzi.android.library")
    id("platzi.library.jacoco")
    id("platzi.android.hilt")
}

android {
    namespace = "com.platzi.randomcats.core.network"
}

dependencies {
    implementation(libs.okhttp3)
    implementation(libs.moshi)
}