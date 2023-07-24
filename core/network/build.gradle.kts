plugins {
    id("platzi.android.library")
    id("platzi.library.hilt")
}

android {
    namespace = "com.platzi.randomcats.core.network"
}

dependencies {
    implementation(project(":core:common"))

    implementation(libs.moshi)
    implementation(libs.moshi.converter)
    implementation(libs.retrofit)
}