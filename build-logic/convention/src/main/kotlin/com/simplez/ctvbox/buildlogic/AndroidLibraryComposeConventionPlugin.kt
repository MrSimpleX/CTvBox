package com.simplez.ctvbox.buildlogic

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryComposeConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("ctvbox.android.library")
            pluginManager.apply("org.jetbrains.kotlin.plugin.compose")

            val libs = libs()

            extensions.configure<LibraryExtension> {
                defaultConfig {
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }
                buildFeatures {
                    compose = true
                }
            }

            addImplementation(libs, "androidx-core-ktx")
            addImplementation(libs, "androidx-lifecycle-runtime-ktx")
            addImplementation(libs, "androidx-lifecycle-runtime-compose")
            addImplementation(libs, "androidx-lifecycle-viewmodel-ktx")
            addComposeUiDependencies(libs, includeUiGraphics = false)
            addCommonTestDependencies(libs)
            addCommonAndroidTestDependencies(libs)
            addCommonComposeDebugDependencies(libs)
        }
    }
}
