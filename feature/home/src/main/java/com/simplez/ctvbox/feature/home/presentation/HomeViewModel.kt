package com.simplez.ctvbox.feature.home.presentation

import androidx.lifecycle.viewModelScope
import com.simplez.ctvbox.core.mvi.BaseMviViewModel
import com.simplez.ctvbox.core.result.AppResult
import com.simplez.ctvbox.core.result.userMessage
import com.simplez.ctvbox.feature.home.domain.GetTodosPageUseCase
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getTodosPageUseCase: GetTodosPageUseCase
) : BaseMviViewModel<HomeIntent, HomeState, HomeEffect>(HomeState()) {

    init {
        processIntent(HomeIntent.InitialLoad)
    }

    override fun onIntent(intent: HomeIntent) {
        when (intent) {
            HomeIntent.InitialLoad -> refreshTodos()
            HomeIntent.Refresh -> refreshTodos()
            HomeIntent.LoadNextPage -> loadNextPage()
        }
    }

    private fun refreshTodos() {
        val snapshot = uiState.value.todos
        if (snapshot.isRefreshing || snapshot.isLoadingMore) return

        val firstPage = 1
        val pageSize = snapshot.pageSize
        viewModelScope.launch {
            updateState {
                copy(
                    todos = todos.copy(
                        isRefreshing = true,
                        isLoadingMore = false,
                        endReached = false,
                        page = firstPage
                    ),
                    errorMessage = null
                )
            }

            when (val result = getTodosPageUseCase(page = firstPage, pageSize = pageSize)) {
                is AppResult.Success -> {
                    val nextPage = firstPage + 1
                    val endReached = result.data.size < pageSize
                    updateState {
                        copy(
                            todos = todos.copy(
                                items = result.data,
                                page = nextPage,
                                endReached = endReached,
                                isRefreshing = false,
                                isLoadingMore = false
                            ),
                            errorMessage = null
                        )
                    }
                }

                is AppResult.Error -> {
                    val errorText = result.error.userMessage()
                    updateState {
                        copy(
                            todos = todos.copy(
                                isRefreshing = false,
                                isLoadingMore = false
                            ),
                            errorMessage = errorText
                        )
                    }
                    postEffect(HomeEffect.ShowErrorToast(errorText))
                }
            }
        }
    }

    private fun loadNextPage() {
        val snapshot = uiState.value.todos
        if (snapshot.isRefreshing || snapshot.isLoadingMore || snapshot.endReached) return

        val requestPage = snapshot.page
        val pageSize = snapshot.pageSize
        val oldItems = snapshot.items

        viewModelScope.launch {
            updateState {
                copy(
                    todos = todos.copy(isLoadingMore = true),
                    errorMessage = null
                )
            }

            when (val result = getTodosPageUseCase(page = requestPage, pageSize = pageSize)) {
                is AppResult.Success -> {
                    val mergedItems = oldItems + result.data
                    val endReached = result.data.size < pageSize
                    updateState {
                        copy(
                            todos = todos.copy(
                                items = mergedItems,
                                page = requestPage + 1,
                                endReached = endReached,
                                isLoadingMore = false
                            ),
                            errorMessage = null
                        )
                    }
                }

                is AppResult.Error -> {
                    val errorText = result.error.userMessage()
                    updateState {
                        copy(
                            todos = todos.copy(isLoadingMore = false),
                            errorMessage = errorText
                        )
                    }
                    postEffect(HomeEffect.ShowErrorToast(errorText))
                }
            }
        }
    }
}
