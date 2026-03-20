plugins {
    id("ctvbox.android.library.compose")
}

android {
    namespace = "com.simplez.ctvbox.feature.home"
}

dependencies {
    implementation(project(":core"))

    implementation(libs.io.insert.koin.android)
    implementation(libs.io.insert.koin.androidx.compose)
    implementation(libs.io.ktor.client.core)
    implementation(libs.kotlinx.serialization.json)

    testImplementation(libs.kotlinx.coroutines.test)
}
