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
    implementation(projects.core.navgation)

    implementation(projects.feature.home.impl)

    implementation(libs.androidx.core.splashscreen)
    implementation(libs.io.insert.koin.android)
}
