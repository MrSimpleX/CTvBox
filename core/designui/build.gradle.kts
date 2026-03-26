plugins {
    alias(libs.plugins.ctvbox.android.library.compose)
    alias(libs.plugins.ctvbox.android.library)
}

android {
    namespace = "com.simplez.ctvbox.designui"
}

dependencies {
    api(libs.androidx.compose.material3)

    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.io.insert.koin.core)
    implementation(libs.io.ktor.client.core)
    implementation(libs.io.ktor.client.cio)
    implementation(libs.io.ktor.client.logging)
    implementation(libs.io.ktor.client.content.negotiation)
    implementation(libs.io.ktor.serialization.kotlinx.json)
    implementation(libs.kotlinx.serialization.json)
}
