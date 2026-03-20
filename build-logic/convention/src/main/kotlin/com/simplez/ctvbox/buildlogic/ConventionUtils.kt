package com.simplez.ctvbox.buildlogic

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

internal fun Project.libs(): VersionCatalog {
    return extensions.getByType<VersionCatalogsExtension>().named("libs")
}

private fun configureCommonAndroid(
    compileSdkSetter: (Int) -> Unit,
    minSdkSetter: (Int) -> Unit,
    compileOptionsSetter: (JavaVersion, JavaVersion) -> Unit
) {
    compileSdkSetter(36)
    minSdkSetter(24)
    compileOptionsSetter(JavaVersion.VERSION_11, JavaVersion.VERSION_11)
}

internal fun ApplicationExtension.configureCommonAndroid() {
    configureCommonAndroid(
        compileSdkSetter = { compileSdk = it },
        minSdkSetter = { defaultConfig { minSdk = it } },
        compileOptionsSetter = { source, target ->
            compileOptions {
                sourceCompatibility = source
                targetCompatibility = target
            }
        }
    )
}

internal fun LibraryExtension.configureCommonAndroid() {
    configureCommonAndroid(
        compileSdkSetter = { compileSdk = it },
        minSdkSetter = { defaultConfig { minSdk = it } },
        compileOptionsSetter = { source, target ->
            compileOptions {
                sourceCompatibility = source
                targetCompatibility = target
            }
        }
    )
}

internal fun Project.addImplementation(libs: VersionCatalog, alias: String) {
    dependencies.add("implementation", libs.findLibrary(alias).get())
}

internal fun Project.addTestImplementation(libs: VersionCatalog, alias: String) {
    dependencies.add("testImplementation", libs.findLibrary(alias).get())
}

internal fun Project.addAndroidTestImplementation(libs: VersionCatalog, alias: String) {
    dependencies.add("androidTestImplementation", libs.findLibrary(alias).get())
}

internal fun Project.addDebugImplementation(libs: VersionCatalog, alias: String) {
    dependencies.add("debugImplementation", libs.findLibrary(alias).get())
}

internal fun Project.addComposeUiDependencies(libs: VersionCatalog, includeUiGraphics: Boolean) {
    dependencies.add(
        "implementation",
        dependencies.platform(libs.findLibrary("androidx-compose-bom").get())
    )
    addImplementation(libs, "androidx-compose-ui")
    if (includeUiGraphics) {
        addImplementation(libs, "androidx-compose-ui-graphics")
    }
    addImplementation(libs, "androidx-compose-ui-tooling-preview")
    addImplementation(libs, "androidx-compose-material3")
}

internal fun Project.addCommonTestDependencies(libs: VersionCatalog) {
    addTestImplementation(libs, "junit")
}

internal fun Project.addCommonAndroidTestDependencies(libs: VersionCatalog) {
    addAndroidTestImplementation(libs, "androidx-junit")
    addAndroidTestImplementation(libs, "androidx-espresso-core")
    dependencies.add(
        "androidTestImplementation",
        dependencies.platform(libs.findLibrary("androidx-compose-bom").get())
    )
    addAndroidTestImplementation(libs, "androidx-compose-ui-test-junit4")
}

internal fun Project.addCommonComposeDebugDependencies(libs: VersionCatalog) {
    addDebugImplementation(libs, "androidx-compose-ui-tooling")
    addDebugImplementation(libs, "androidx-compose-ui-test-manifest")
}