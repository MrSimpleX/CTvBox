plugins {
    id("ctvbox.android.application.compose")
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
    implementation(project(":core:core"))
    implementation(project(":feature:home:impl"))

    implementation(libs.io.insert.koin.android)
}
