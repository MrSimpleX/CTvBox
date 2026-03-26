plugins {
    id("ctvbox.android.library")
    id("ctvbox.android.library.compose")
}

android {
    namespace = "com.simplez.ctvbox.navigation"
}

dependencies {
    api(libs.androidx.navigation3.runtime)
}
