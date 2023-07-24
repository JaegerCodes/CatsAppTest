plugins {
    id("platzi.android.library")
    id("platzi.library.jacoco")
    id("platzi.library.hilt")
}

android {
    namespace = "com.platzi.catshome.data"
}

dependencies {
    androidTestImplementation(project(":core:testing"))
}