plugins {
    id("ctvbox.android.library")
}

android {
    namespace = "com.simplez.ctvbox.navigation"
}

dependencies {
    api(libs.androidx.navigation3.runtime)
}
