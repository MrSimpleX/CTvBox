package com.simplez.ctvbox.feature.home.presentation

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.simplez.ctvbox.core.mvi.PaginationState
import com.simplez.ctvbox.feature.home.domain.TodoItem
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeScreenTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun refreshingWithEmptyList_showsInitialLoading() {
        composeRule.setContent {
            MaterialTheme {
                HomeScreen(
                    state = HomeState(
                        todos = PaginationState(
                            items = emptyList(),
                            isRefreshing = true,
                            pageSize = 12
                        )
                    ),
                    onRefresh = {},
                    onLoadMore = {}
                )
            }
        }

        composeRule.onNodeWithTag("home_loading").assertIsDisplayed()
    }

    @Test
    fun withError_showsErrorMessage() {
        composeRule.setContent {
            MaterialTheme {
                HomeScreen(
                    state = HomeState(
                        todos = PaginationState(pageSize = 12),
                        errorMessage = "Network unavailable"
                    ),
                    onRefresh = {},
                    onLoadMore = {}
                )
            }
        }

        composeRule.onNodeWithTag("home_error")
            .assertIsDisplayed()
            .assertTextContains("Network unavailable")
    }

    @Test
    fun endReached_showsNoMoreData_andHidesLoadMore() {
        composeRule.setContent {
            MaterialTheme {
                HomeScreen(
                    state = HomeState(
                        todos = PaginationState(
                            items = listOf(TodoItem(1, "todo-1", false)),
                            endReached = true,
                            pageSize = 12
                        )
                    ),
                    onRefresh = {},
                    onLoadMore = {}
                )
            }
        }

        composeRule.onNodeWithTag("home_no_more").assertIsDisplayed()
        assertTrue(composeRule.onAllNodesWithTag("home_load_more").fetchSemanticsNodes().isEmpty())
    }

    @Test
    fun clickLoadMore_invokesCallback() {
        var clicked = false

        composeRule.setContent {
            MaterialTheme {
                HomeScreen(
                    state = HomeState(
                        todos = PaginationState(
                            items = listOf(TodoItem(1, "todo-1", false)),
                            endReached = false,
                            isLoadingMore = false,
                            pageSize = 12
                        )
                    ),
                    onRefresh = {},
                    onLoadMore = { clicked = true }
                )
            }
        }

        composeRule.onNodeWithTag("home_load_more").performClick()

        composeRule.runOnIdle {
            assertTrue(clicked)
        }
    }
}

