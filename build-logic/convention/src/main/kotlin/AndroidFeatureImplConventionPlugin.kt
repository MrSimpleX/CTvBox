import com.android.build.api.dsl.LibraryExtension
import com.simplez.ctvbox.buildlogic.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.internal.Actions.with
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

/**
 * @Title: CTvBox
 * @Package com.simplez.ctvbox.buildlogic
 * @Description:
 * @author SimpleZ
 * @date 2026/3/25 10:58
 * @version V1.0
 */
class AndroidFeatureImplConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "ctvbox.android.library")

            extensions.configure<LibraryExtension> {
                testOptions.animationsDisabled = true
            }

            dependencies {
                "implementation"(project(":core:designui"))
                "implementation"(project(":core:core"))

                "implementation"(libs.findLibrary("androidx.lifecycle.runtime.compose").get())
                "implementation"(libs.findLibrary("androidx.lifecycle.viewModel.compose").get())
                "implementation"(libs.findLibrary("androidx.navigation3.runtime").get())

                "androidTestImplementation"(
                    libs.findLibrary("androidx.lifecycle.runtime.testing").get(),
                )
            }
        }
    }
}