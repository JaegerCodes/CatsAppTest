plugins {
    id("platzi.android.library")
    id("platzi.library.jacoco")
    id("platzi.android.hilt")
    id("platzi.android.room")
}

android {
    namespace = "com.platzi.randomcats.core.database"
}

dependencies {
    implementation(libs.room.paging)
}