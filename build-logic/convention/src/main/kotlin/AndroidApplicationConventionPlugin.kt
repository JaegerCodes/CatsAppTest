import com.android.build.api.dsl.ApplicationExtension
import com.platzi.randomcats.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import java.io.FileInputStream
import java.util.*

/**
 * A Gradle plugin that applies the Android Application configurations and conventions
 * for an Android application project.
 *
 * This plugin should be applied to an Android application module to configure
 * the basic Android and Kotlin settings, as well as the versioning information
 * for the application. The plugin is responsible for setting up the Android
 * application module with the necessary configurations, such as targetSdk version,
 * versionCode, and versionName, as well as applying the required plugins.
 *
 * Usage:
 *
 * 1. Register the plugin in the build.gradle.kts or build.gradle file of the
 *    root project, using the gradlePlugin block:
 *
 * ```
 * gradlePlugin {
 *     plugins {
 *         register("androidApplication") {
 *             id = "platzi.android.application"
 *             implementationClass = "AndroidApplicationConventionPlugin"
 *         }
 *     }
 * }
 * ```
 *
 * 2. In the build.gradle.kts file of an Android application module, apply the plugin
 *    using the Project.apply() function or the plugins block:
 *
 * ```
 * apply(plugin = "platzi.android.application")
 * ```
 *
 */
class AndroidApplicationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            val appVersionFile = rootProject.file("delivery/version.properties")
            val versionProperties = Properties()
            versionProperties.load(FileInputStream(appVersionFile))

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)

                defaultConfig.apply {
                    targetSdk = 33
                    versionCode = versionProperties["CODE"].toString().toInt()
                    versionName = "${versionProperties["MAJOR"]}.${versionProperties["MINOR"]}.${versionProperties["PATCH"]}"
                }
            }

            dependencies {
                add("testImplementation", project(":core:testing"))
                add("androidTestImplementation", project(":core:testing"))
            }
        }
    }
}