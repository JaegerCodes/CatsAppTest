import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.FileInputStream
import java.text.SimpleDateFormat
import java.util.Properties
import java.util.Locale
import java.util.Date

class ReleaseNotesConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            tasks.register("generateReleaseNotes") {
                group = "ReleaseNotes"
                description = "Run this task to create release notes"

                println("Generating release notes...")

                val appVersionFile = rootProject.file("delivery/version.properties")

                val versionProperties = Properties()
                versionProperties.load(FileInputStream(appVersionFile))

                doLast {
                    val releaseNotes = file("firebase-app-distribution/CHANGELOG.md")
                    releaseNotes.delete()

                    val versionName = System.getenv("VERSION_NAME") ?:
                    "${versionProperties["MAJOR"]}.${versionProperties["MINOR"]}.${versionProperties["PATCH"]}"

                    val versionDate = SimpleDateFormat("dd/MM/yyyy - hh:mm aa", Locale.getDefault()).format(Date())

                    val process = ProcessBuilder()
                        .command("git log --format=\"[%an] - %s\" 67bcd6e..HEAD".split(" "))
                        .directory(rootProject.projectDir)
                        .redirectOutput(ProcessBuilder.Redirect.INHERIT)
                        .redirectError(ProcessBuilder.Redirect.INHERIT)
                        .start()

                    var changelog = ""

                    process.inputStream.bufferedReader().readLines().forEach { changelog += "* $it\n" }

                    releaseNotes.writeText("""
                        |Release Notes - Version $versionName ($versionDate)
                        |Change Log:

                        |$changelog
                        |This file was automatically generated.
                    """.trimMargin())

                    println("Release notes generated.")
                }
            }
        }
    }
}