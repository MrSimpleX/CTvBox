package com.simplez.ctvbox.feature.home.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.simplez.api.MineNavKey
import com.simplez.ctvbox.feature.home.presentation.MinePage
import com.simplez.navigation.Navigator

/**
 * @Title: CTvBox
 * @Package com.simplez.ctvbox.feature.mine.navigation
 * @Description:
 * @author SimpleZ
 * @date 2026/3/26 14:55
 * @version V1.0
 */
fun EntryProviderScope<NavKey>.mineEntry(navigator: Navigator) {
    entry<MineNavKey> {
        MinePage()
    }
}