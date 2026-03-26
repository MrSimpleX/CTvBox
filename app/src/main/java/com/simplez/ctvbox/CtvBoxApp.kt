package com.simplez.ctvbox

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.simplez.ctvbox.feature.home.navigation.homeEntry
import com.simplez.navigation.NavigationState
import com.simplez.navigation.Navigator
import com.simplez.navigation.toEntries

/**
 * @Title: CTvBox
 * @Package com.simplez.ctvbox
 * @Description: App 入口
 * @author SimpleZ
 * @date 2026/3/25 10:42
 * @version V1.0
 */
@Composable
fun CtvBoxApp(navigationState: NavigationState) {
    Scaffold() {
        Column(modifier = Modifier.padding(it)) {

            val navigator = remember { Navigator(navigationState) }

            val entryProvider = entryProvider {
                homeEntry(navigator)
            }

            //val listDetailStrategy = rememberListDetailSceneStrategy<NavKey>()

            NavDisplay(
                entries = navigationState.toEntries(entryProvider),
                //sceneStrategy = listDetailStrategy,
                onBack = { navigator.goBack() },
            )
        }
    }

}