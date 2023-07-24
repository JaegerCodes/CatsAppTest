package com.platzi.randomcats

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

/**
 * Apply Hilt dependencies to the project.
 */
internal fun Project.configureAndroidHilt(commonExtension: CommonExtension<*, *, *, *>) {
    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

    commonExtension.apply {

        dependencies {
            add("implementation", libs.findLibrary("hilt-android").get())
            add("kapt", libs.findLibrary("hilt-compiler").get())
            add("kaptAndroidTest", libs.findLibrary("hilt-compiler").get())
        }
    }
}
