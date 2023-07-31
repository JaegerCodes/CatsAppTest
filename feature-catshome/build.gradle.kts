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


    // testing
    testImplementation(libs.mockk)
    testImplementation(libs.junit)
    testImplementation(libs.mockwebserver)

    // AndroidX Test dependencies
    androidTestImplementation(libs.junit.ext)
    androidTestImplementation(libs.espresso.core)

    // kotlinx-coroutines-test for testing coroutines
    testImplementation(libs.coroutine.test)
}
