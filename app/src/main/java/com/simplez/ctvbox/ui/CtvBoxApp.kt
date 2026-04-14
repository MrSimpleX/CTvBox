package com.simplez.ctvbox.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.simplez.ctvbox.feature.home.navigation.findEntry
import com.simplez.ctvbox.feature.home.navigation.homeEntry
import com.simplez.ctvbox.feature.home.navigation.mineEntry
import com.simplez.ctvbox.navigation.NAV_ITEMS
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
    val navigator = remember { Navigator(navigationState) }
    val showBottomBar = navigationState.currentKey in NAV_ITEMS.keys

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    NAV_ITEMS.forEach { (key, item) ->
                        val selected = navigationState.currentTopLevelKey == key
                        NavigationBarItem(
                            selected = selected,
                            onClick = { navigator.navigate(key) },
                            icon = {
                                Icon(
                                    imageVector = if (selected) {
                                        item.selectedIcon
                                    } else {
                                        item.unselectedIcon
                                    },
                                    contentDescription = item.name,
                                )
                            },
                            label = {
                                Text(text = item.name)
                            },
                        )
                    }
                }
            }
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            val entryProvider = entryProvider {
                homeEntry(navigator)
                mineEntry(navigator)
                findEntry(navigator)
            }

            NavDisplay(
                entries = navigationState.toEntries(entryProvider),
                onBack = { navigator.goBack() },
            )
        }
    }
}
