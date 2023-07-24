package com.platzi.randomcats

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

/**
 * Configures the Kotlin Android settings for the given project using the specified
 * CommonExtension instance.
 *
 * This function applies the required Kotlin Android configurations for the project,
 * including setting the compileSdk version, minSdk version, testInstrumentationRunner,
 * vectorDrawables support, Java and Kotlin source/target compatibility, and packaging options.
 *
 * @param commonExtension The CommonExtension instance (either BaseExtension or
 *                        LibraryExtension) used to configure the Android project.
 */
internal fun Project.configureKotlinAndroid(commonExtension: CommonExtension<*, *, *, *>) {
    commonExtension.apply {
        compileSdk = 33

        defaultConfig {
            minSdk = 24

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            vectorDrawables {
                useSupportLibrary = true
            }
        }

        compileOptions {
            sourceCompatibility(JavaVersion.VERSION_1_8)
            targetCompatibility(JavaVersion.VERSION_1_8)
        }

        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_1_8.toString()
        }

        packagingOptions {
            resources {
                excludes.add("/META-INF/{AL2.0,LGPL2.1}")
            }
        }
    }
}

/**
 * Configures the Kotlin JVM options for the given CommonExtension instance.
 *
 * This function should be used within the scope of a CommonExtension instance (either
 * BaseExtension or LibraryExtension) to configure the Kotlin JVM options.
 *
 * @param block The configuration block for the KotlinJvmOptions.
 */
private fun CommonExtension<*, *, *, *>.kotlinOptions(block: KotlinJvmOptions.() -> Unit) {
    (this as ExtensionAware).extensions.configure("kotlinOptions", block)
}