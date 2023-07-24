import com.android.build.api.dsl.ApplicationExtension
import com.platzi.randomcats.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

/**
 * A Gradle plugin that applies the Android Compose configurations and conventions
 * for an Android application project.
 *
 * This plugin should be applied to an Android application module to configure
 * the Compose-related settings and dependencies using the conventions defined
 * in the configureAndroidCompose function. The plugin is responsible for
 * configuring the Android application module to work correctly with Android Compose.
 *
 * Usage:
 *
 * 1. Register the plugin in the build.gradle.kts or build.gradle file of the
 *    root project, using the gradlePlugin block:
 *
 * ```
 * gradlePlugin {
 *     plugins {
 *         register("androidApplicationCompose") {
 *             id = "platzi.android.compose"
 *             implementationClass = "AndroidApplicationComposeConventionPlugin"
 *         }
 *     }
 * }
 * ```
 *
 * 2. In the build.gradle.kts file of an Android application module, apply the plugin
 *    using the Project.apply() function or the plugins block:
 *
 * ```
 * apply(plugin = "platzi.android.compose")
 * ```
 *
 */
class AndroidApplicationComposeConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            extensions.configure<ApplicationExtension> {
                configureAndroidCompose(this)
            }
        }
    }
}