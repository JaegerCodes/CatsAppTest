import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import com.platzi.randomcats.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationComposeConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            val libraryExtension = extensions.findByType(LibraryExtension::class.java)

            if (libraryExtension != null) {
                configureAndroidCompose(libraryExtension)
            } else {
                extensions.configure<ApplicationExtension> {
                    configureAndroidCompose(this)
                }
            }
        }
    }
}