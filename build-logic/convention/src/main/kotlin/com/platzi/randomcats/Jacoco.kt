package com.platzi.randomcats

import com.android.build.api.variant.AndroidComponentsExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.tasks.testing.Test
import org.gradle.configurationcache.extensions.capitalized
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.gradle.testing.jacoco.plugins.JacocoPluginExtension
import org.gradle.testing.jacoco.plugins.JacocoTaskExtension
import org.gradle.testing.jacoco.tasks.JacocoCoverageVerification
import org.gradle.testing.jacoco.tasks.JacocoReport
import org.jetbrains.kotlin.com.google.gson.Gson
import org.jetbrains.kotlin.com.google.gson.stream.JsonReader
import java.io.File
import java.io.FileReader
import java.math.BigDecimal

private val coverageExclusions = listOf(
    // Binding
    "android/databinding/**/*.class",
    "**/android/databinding/*Binding.class",
    "**/android/databinding/*",
    "**/androidx/databinding/*",
    "**/BR.*",
    "**/*Binding.class",
    // Android
    "**/R.class",
    "**/R\$*.class",
    "**/BuildConfig.*",
    "**/Manifest*.*",
    "**/*Test*.*",
    "android/**/*.*",
    "**/*FragmentDirections*.*",
    "**/*FragmentArgs*.*",
    // dagger
    "**/*MembersInjector*.*",
    "**/*_MembersInjector.class",
    "**/Dagger*Component.class",
    "**/Dagger*Subcomponent*.class",
    "**/*Subcomponent\$Builder.class",
    "**/Dagger*Component\$Builder.class",
    "**/*Module_*Factory.class",
    "**/di/module/*",
    "**/*_Factory*.*",
    "**/*Module*.*",
    "**/*Dagger*.*",
    "**/*Hilt*.*",
    "**/*_MembersInjector*.*",
    "**/*_*Factory*.*",
    "**/*_Provide*Factory*.*",
    "**/*Component*.*",
    "**/*Extensions*.*",
    "**/*_GeneratedInjector.class",
    "**/*_ApplicationModule.class",
    "**/dagger/hilt/*",
    // kotlin
    "**/*MapperImpl*.*",
    "**/*\$ViewInjector*.*",
    "**/*\$ViewBinder*.*",
    "**/*BR*.*",
    "**/*\$Lambda$*.*",
    "**/Lambda$*.class",
    "**/Lambda.class",
    "**/*Lambda.class",
    "**/*Lambda*.class",
    "**/*Companion*.*",
    // sealed and data classes
    "**/*\$Result.*",
    "**/*\$Result$*.*",
    // adapters generated by moshi
    "**/*JsonAdapter.*",
)

internal fun Project.configureModuleJacoco(
    androidComponentsExtension: AndroidComponentsExtension<*, *, *>
) {
    configureJacoco()

    androidComponentsExtension.onVariants { variant ->
        val jacocoRulesFile = FileReader("${rootProject.projectDir}/gradle/coverage-rules.json")
        val jacocoRules = Gson().fromJson<JacocoRules>(JsonReader(jacocoRulesFile), JacocoRules::class.java)

        if (variant.buildType == "debug") {
            val variantName = if (variant.flavorName.isNullOrEmpty()) "debug" else "${variant.flavorName}Debug"

            createCoverageTask(
                "test${variant.flavorName?.capitalized() ?: ""}DebugUnitTest",
                variantName,
                variant.flavorName ?: "",
                jacocoRules
            )
        }
    }
}

private fun Project.configureJacoco() {
    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

    configure<JacocoPluginExtension> {
        toolVersion = libs.findVersion("jacocoVersion").get().toString()
    }

    tasks.withType<Test>().configureEach {
        configure<JacocoTaskExtension> {
            isIncludeNoLocationClasses = true
            excludes = listOf("jdk.internal.*")
        }
    }
}

private fun Project.createCoverageTask(testTaskName: String, variantName: String, flavorName: String, jacocoRules: JacocoRules) {
    val jacocoCoverage = tasks.create("jacoco${flavorName.capitalized()}Coverage", JacocoReport::class.java) {
        dependsOn(testTaskName)

        group = "Reporting"
        description = "Generate Jacoco coverage reports for the ${variantName.capitalized()} build."

        reports {
            html.required.set(true)
            xml.required.set(true)
        }

        classDirectories.setFrom(files(
            fileTree("$buildDir/intermediates/javac/$variantName") { exclude(coverageExclusions) },
            fileTree("$buildDir/tmp/kotlin-classes/$variantName") { exclude(coverageExclusions) }
        ))

        sourceDirectories.setFrom(files(
            "$projectDir/src/main/java",
            "$projectDir/src/$variantName/java",
            "$projectDir/src/main/kotlin",
            "$projectDir/src/$variantName/kotlin"
        ))

        executionData.setFrom(files(
            "$buildDir/jacoco/$testTaskName.exec",
            "$buildDir/outputs/unit_test_code_coverage/debugUnitTest/$testTaskName.exec"
        ))

        doLast {
            println(variantName)
            println(flavorName)
            println(File("$buildDir/reports/jacoco/jacoco${flavorName.capitalized()}Coverage/html/index.html"))
        }
    }

    tasks.create("jacoco${flavorName.capitalized()}CoverageVerification", JacocoCoverageVerification::class.java) {
        dependsOn(jacocoCoverage)

        group = "Reporting"
        description = "Verifies Jacoco coverage for the ${variantName.capitalized()} build."

        violationRules {
            rule {
                limit {
                    counter = "INSTRUCTION"
                    minimum = getMinBranchCodeCoverage(jacocoRules, this@createCoverageTask.name)
                }
            }

            rule {
                limit {
                    counter = "BRANCH"
                    minimum = getMinInstructionCodeCoverage(jacocoRules, this@createCoverageTask.name)
                }
            }
        }

        classDirectories.setFrom(files(
            fileTree("$buildDir/intermediates/javac/$variantName") { exclude(coverageExclusions) },
            fileTree("$buildDir/tmp/kotlin-classes/$variantName") { exclude(coverageExclusions) }
        ))

        sourceDirectories.setFrom(files(
            "$projectDir/src/main/java",
            "$projectDir/src/$variantName/java",
            "$projectDir/src/main/kotlin",
            "$projectDir/src/$variantName/kotlin"
        ))

        executionData.setFrom(files(
            "$buildDir/jacoco/$testTaskName.exec",
            "$buildDir/outputs/unit_test_code_coverage/debugUnitTest/$testTaskName.exec"
        ))
    }
}

private fun getMinBranchCodeCoverage(jacocoRules: JacocoRules, projectName: String): BigDecimal = (
        jacocoRules.branches.modules.find { it.module == projectName }?.threshold ?: jacocoRules.branches.threshold
        ).toBigDecimal()

private fun getMinInstructionCodeCoverage(jacocoRules: JacocoRules, projectName: String): BigDecimal = (
        jacocoRules.instructions.modules.find { it.module == projectName }?.threshold ?: jacocoRules.instructions.threshold
        ).toBigDecimal()
