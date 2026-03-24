package com.simplez.ctvbox.feature.home.presentation

import com.simplez.ctvbox.core.mvi.PaginationState
import com.simplez.ctvbox.feature.home.domain.TodoItem

sealed interface HomeIntent {
    data object InitialLoad : HomeIntent
    data object Refresh : HomeIntent
    data object LoadNextPage : HomeIntent
}

data class HomeState(
    val todos: PaginationState<TodoItem> = PaginationState(pageSize = 12),
    val errorMessage: String? = null
)

sealed interface HomeEffect {
    data class ShowErrorToast(val message: String) : HomeEffect
}
