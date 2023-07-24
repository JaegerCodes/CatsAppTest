package com.platzi.randomcats

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.kotlin

/**
 * Apply Hilt dependencies to the project.
 */
internal fun Project.configurePlatziFeature(commonExtension: CommonExtension<*, *, *, *>) {
    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

    commonExtension.apply {

        dependencies {
            add("implementation", project(":core:designsystem"))
            add("implementation", project(":core:common"))

            add("testImplementation", kotlin("test"))
            add("testImplementation", project(":core:testing"))
            add("androidTestImplementation", kotlin("test"))
            add("androidTestImplementation", project(":core:testing"))

            add("implementation", libs.findLibrary("coil").get())
            add("implementation", libs.findLibrary("coil-compose").get())

            add("implementation", libs.findLibrary("hilt-android").get())

            add("implementation", libs.findLibrary("hilt-ext-compiler").get())
            add("implementation", libs.findLibrary("hilt-ext-navigation").get())
        }
    }
}
