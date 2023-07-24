
import com.android.build.api.dsl.LibraryExtension
import com.platzi.randomcats.configurePlatziFeature
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {


        with(target) {
            pluginManager.apply {
                apply("platzi.android.library")
                apply("platzi.library.hilt")
            }
            val extension = extensions.getByType<LibraryExtension>()
            configurePlatziFeature(extension)
        }
    }
}

