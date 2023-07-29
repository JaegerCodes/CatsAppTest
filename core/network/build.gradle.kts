plugins {
    id("platzi.android.library")
    id("platzi.library.jacoco")
    id("platzi.android.hilt")
}

android {
    namespace = "com.platzi.randomcats.core.network"
}

dependencies {
    api(libs.okhttp3)
    api(libs.moshi)
    api(libs.retrofit)
    api(libs.retrofit.converter)
}