import com.android.build.api.dsl.ApplicationExtension

import com.platzi.randomcats.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import java.io.FileInputStream
import java.util.*

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
                    targetSdk = 34
                    versionCode = versionProperties["CODE"].toString().toInt()
                    versionName = "${versionProperties["MAJOR"]}.${versionProperties["MINOR"]}.${versionProperties["PATCH"]}"
                }
            }

            dependencies {
                //add("testImplementation", project(":core:testing"))
                //add("androidTestImplementation", project(":core:testing"))
            }
        }
    }
}