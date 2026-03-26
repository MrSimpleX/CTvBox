package com.simplez.ctvbox.feature.home.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.simplez.api.DetailNavKey
import com.simplez.api.HomeNavKey
import com.simplez.ctvbox.feature.home.presentation.DetailScreen
import com.simplez.ctvbox.feature.home.presentation.HomeRoute
import com.simplez.ctvbox.feature.home.presentation.HomeScreen
import com.simplez.navigation.Navigator

/**
 * @Title: CTvBox
 * @Package com.simplez.ctvbox.feature.home.navigation
 * @Description:
 * @author SimpleZ
 * @date 2026/3/26 14:55
 * @version V1.0
 */
fun EntryProviderScope<NavKey>.homeEntry(navigator: Navigator) {
    entry<HomeNavKey> {
        HomeRoute {
            navigator.navigate(DetailNavKey)
        }
    }
    entry<DetailNavKey> {
        DetailScreen()
    }
}