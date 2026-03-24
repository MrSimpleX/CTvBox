/*
 * Copyright 2025 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.simplez.navgation

import androidx.navigation3.runtime.NavKey

/**
 * 导航处理工具类
 *
 * @param state - 导航数据
 */
class Navigator(val state: NavigationState) {

    /**
     * 跳转到指定页面
     *
     * @param key - the navigation key to navigate to.
     */
    fun navigate(key: NavKey) {
        when (key) {
            state.currentTopLevelKey -> clearSubStack()
            in state.topLevelKeys -> goToTopLevel(key)
            else -> goToKey(key)
        }
    }

    /**
     * 返回上一级
     */
    fun goBack() {
        when (state.currentKey) {
            state.startKey -> error("You cannot go back from the start route")
            state.currentTopLevelKey -> {
                // We're at the base of the current sub stack, go back to the previous top level
                // stack.
                state.topLevelStack.removeLastOrNull()
            }

            else -> state.currentSubStack.removeLastOrNull()
        }
    }

    /**
     * Go to a non top level key.
     */
    private fun goToKey(key: NavKey) {
        state.currentSubStack.apply {
            // Remove it if it's already in the stack so it's added at the end.
            remove(key)
            add(key)
        }
    }

    /**
     * 切换一级导航
     */
    private fun goToTopLevel(key: NavKey) {
        state.topLevelStack.apply {
            if (key == state.startKey) {
                // This is the start key. Clear the stack so it's added as the only key.
                clear()
            } else {
                // Remove it if it's already in the stack so it's added at the end.
                remove(key)
            }
            add(key)
        }
    }

    /**
     * 清空二级栈
     */
    private fun clearSubStack() {
        state.currentSubStack.run {
            if (size > 1) subList(1, size).clear()
        }
    }
}
