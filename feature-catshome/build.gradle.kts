plugins {
    id("platzi.android.library")
    id("platzi.library.jacoco")
    id("platzi.android.hilt")
    id("platzi.android.room")
    id("platzi.android.compose")
}

android {
    namespace = "com.platzi.feature.catshome"

}

dependencies {

    implementation(project(":core:database"))
    implementation(project(":core:network"))

    implementation(libs.paging.runtime)
    implementation(libs.hilt.ext.navigation)

    implementation(libs.coil)
    implementation(libs.paging.compose)

}