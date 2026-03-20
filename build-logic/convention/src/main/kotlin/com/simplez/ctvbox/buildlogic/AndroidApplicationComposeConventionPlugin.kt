package com.simplez.ctvbox.buildlogic

import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationComposeConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.application")
            pluginManager.apply("org.jetbrains.kotlin.plugin.compose")

            val libs = libs()

            extensions.configure<ApplicationExtension> {
                configureCommonAndroid()
                defaultConfig {
                    targetSdk = 36
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }
                buildTypes {
                    getByName("release") {
                        isMinifyEnabled = false
                        proguardFiles(
                            getDefaultProguardFile("proguard-android-optimize.txt"),
                            "proguard-rules.pro"
                        )
                    }
                }
                buildFeatures {
                    compose = true
                }
            }

            addImplementation(libs, "androidx-core-ktx")
            addImplementation(libs, "androidx-lifecycle-runtime-ktx")
            addImplementation(libs, "androidx-activity-compose")
            addComposeUiDependencies(libs, includeUiGraphics = true)
            addCommonTestDependencies(libs)
            addCommonAndroidTestDependencies(libs)
            addCommonComposeDebugDependencies(libs)
        }
    }
}
