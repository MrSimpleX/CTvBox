package com.simplez.ctvbox.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.AccountCircle
import androidx.compose.material.icons.twotone.Home
import androidx.compose.material.icons.twotone.ScreenSearchDesktop
import androidx.compose.material.icons.twotone.Upcoming
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation3.runtime.NavKey
import com.simplez.api.DetailNavKey
import com.simplez.api.FindNavKey
import com.simplez.api.HomeNavKey
import com.simplez.api.MineNavKey

/**
 * @Title: CTvBox
 * @Package com.simplez.ctvbox.navigation
 * @Description:
 * @author SimpleZ
 * @date 2026/3/26 14:53
 * @version V1.0
 */

data class BoxNavItem(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val name: String,
)

val HOME = BoxNavItem(
    selectedIcon = Icons.TwoTone.Home,
    unselectedIcon = Icons.TwoTone.Home,
    name = "主页"
)

val FIND = BoxNavItem(
    selectedIcon = Icons.TwoTone.ScreenSearchDesktop,
    unselectedIcon = Icons.TwoTone.ScreenSearchDesktop,
    name = "发现"
)

val MINE = BoxNavItem(
    selectedIcon = Icons.TwoTone.AccountCircle,
    unselectedIcon = Icons.TwoTone.AccountCircle,
    name = "我的"
)

val NAV_ITEMS = mapOf<NavKey, BoxNavItem>(
    HomeNavKey to HOME,
    FindNavKey to FIND,
    MineNavKey to MINE,
)