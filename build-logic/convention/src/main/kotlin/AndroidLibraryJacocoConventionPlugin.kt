import com.android.build.api.variant.LibraryAndroidComponentsExtension
import com.platzi.randomcats.configureModuleJacoco
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidLibraryJacocoConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.gradle.jacoco")

            val extension = extensions.getByType<LibraryAndroidComponentsExtension>()
            configureModuleJacoco(extension)
        }
    }
}