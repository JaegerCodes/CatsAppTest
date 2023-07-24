
plugins {
    id("platzi.android.library")
    id("platzi.library.jacoco")
    id("platzi.library.hilt")
}

android {
    namespace = "com.platzi.randomcats.core.common"
}

dependencies {
    androidTestImplementation(project(":core:testing"))
    implementation(libs.moshi.converter)
}
