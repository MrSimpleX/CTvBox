package com.simplez.ctvbox.navigation

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation3.runtime.NavKey
import com.simplez.api.HomeNavKey

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
    @StringRes val iconTextId: Int,
    @StringRes val titleTextId: Int,
)

//val FOR_YOU = BoxNavItem(
//    selectedIcon = NiaIcons.Upcoming,
//    unselectedIcon = NiaIcons.UpcomingBorder,
//    iconTextId = forYouR.string.feature_foryou_api_title,
//    titleTextId = R.string.app_name,
//)
//
//val BOOKMARKS = BoxNavItem(
//    selectedIcon = NiaIcons.Bookmarks,
//    unselectedIcon = NiaIcons.BookmarksBorder,
//    iconTextId = bookmarksR.string.feature_bookmarks_api_title,
//    titleTextId = bookmarksR.string.feature_bookmarks_api_title,
//)
//
//val INTERESTS = BoxNavItem(
//    selectedIcon = NiaIcons.Grid3x3,
//    unselectedIcon = NiaIcons.Grid3x3,
//    iconTextId = searchR.string.feature_search_api_interests,
//    titleTextId = searchR.string.feature_search_api_interests,
//)


val NAV_ITEMS = mapOf<NavKey, NavKey>(
    HomeNavKey to HomeNavKey,
    HomeNavKey to HomeNavKey,
)