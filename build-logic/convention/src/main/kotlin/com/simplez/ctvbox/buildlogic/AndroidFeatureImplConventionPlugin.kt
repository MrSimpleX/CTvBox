package com.simplez.ctvbox.buildlogic

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.internal.Actions.with
import org.gradle.kotlin.dsl.apply

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
            apply(plugin = "org.jetbrains.kotlin.plugin.serialization")
        }
    }
}