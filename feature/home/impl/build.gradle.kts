plugins {
    alias(libs.plugins.ctvbox.android.feature.impl)
    alias(libs.plugins.ctvbox.android.library.compose)
}

android {
    namespace = "com.simplez.ctvbox.feature.home"
}

dependencies {
    implementation(projects.core.core)
    implementation(projects.feature.home.api)

    implementation(libs.io.insert.koin.android)
    implementation(libs.io.insert.koin.androidx.compose)
    implementation(libs.io.ktor.client.core)
    implementation(libs.kotlinx.serialization.json)

    testImplementation(libs.kotlinx.coroutines.test)
}
