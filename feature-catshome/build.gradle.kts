plugins {
    id("platzi.android.library")
    id("platzi.library.jacoco")
    id("platzi.android.hilt")
    id("platzi.android.room")
}

android {
    namespace = "com.platzi.feature.catshome"

}

dependencies {

    implementation(project(":core:database"))

    implementation(libs.okhttp3)
    implementation(libs.moshi)
    implementation(libs.moshi.converter)
    implementation(libs.retrofit)
    implementation(libs.paging.runtime)
}