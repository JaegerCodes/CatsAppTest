import com.android.build.api.dsl.LibraryExtension
import com.platzi.randomcats.configureAndroidHilt
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

/**
 * AndroidHiltConventionPlugin is a Gradle plugin that automatically configures
 * the required dependencies and plugins for using Hilt in Android projects.
 */
class AndroidLibraryHiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("kotlin-kapt")
            val extension = extensions.getByType<LibraryExtension>()
            configureAndroidHilt(extension)
        }
    }
}