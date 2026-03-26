plugins {
    id("ctvbox.android.application.compose")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.simplez.ctvbox"

    defaultConfig {
        applicationId = "com.simplez.ctvbox"
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    implementation(projects.core.core)
    implementation(projects.core.designui)
    implementation(projects.core.navigation)

    implementation(projects.feature.home.impl)
    implementation(projects.feature.home.api)

    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.navigation3.ui)
    implementation(libs.io.insert.koin.android)
}
