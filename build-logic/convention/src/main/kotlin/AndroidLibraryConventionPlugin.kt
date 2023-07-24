import com.android.build.api.dsl.LibraryExtension
import com.platzi.randomcats.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

/**
 * A Gradle plugin that applies the Android Library configurations and conventions
 * for an Android library project.
 *
 * This plugin should be applied to an Android library module to configure
 * the basic Android and Kotlin settings. The plugin is responsible for setting
 * up the Android library module with the necessary configurations, such as
 * the targetSdk version, as well as applying the required plugins.
 *
 * Usage:
 *
 * 1. Register the plugin in the build.gradle.kts or build.gradle file of the
 *    root project, using the gradlePlugin block:
 *
 * ```
 * gradlePlugin {
 *     plugins {
 *         register("androidLibrary") {
 *             id = "platzi.android.library"
 *             implementationClass = "AndroidLibraryConventionPlugin"
 *         }
 *     }
 * }
 * ```
 *
 * 2. In the build.gradle.kts file of an Android application module, apply the plugin
 *    using the Project.apply() function or the plugins block:
 *
 * ```
 * apply(plugin = "platzi.android.library")
 * ```
 *
 */
class AndroidLibraryConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<LibraryExtension>() {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = 33
            }
        }
    }
}