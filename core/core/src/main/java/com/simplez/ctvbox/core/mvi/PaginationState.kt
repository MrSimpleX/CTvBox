package com.simplez.ctvbox.core.mvi

data class PaginationState<T>(
    val items: List<T> = emptyList(),
    val page: Int = 1,
    val pageSize: Int = 10,
    val endReached: Boolean = false,
    val isRefreshing: Boolean = false,
    val isLoadingMore: Boolean = false
)
