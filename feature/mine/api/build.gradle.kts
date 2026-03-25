plugins {
    alias(libs.plugins.ctvbox.android.feature.api)
}

android {
    namespace = "com.simplez.ctvbox.feature.mine.api"
}

dependencies {
    api(projects.core.navigation)
}

