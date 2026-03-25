import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

/**
 * @Title: CTvBox
 * @Package com.simplez.ctvbox.buildlogic
 * @Description:
 * @author SimpleZ
 * @date 2026/3/25 10:53
 * @version V1.0
 */
class AndroidFeatureApiConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "ctvbox.android.library")
            apply(plugin = "org.jetbrains.kotlin.plugin.serialization")

            dependencies {
                "api"(project(":core:navigation"))
            }
        }
    }
}
