package com.simplez.ctvbox.feature.home.domain

import com.simplez.ctvbox.core.result.AppResult
import com.simplez.ctvbox.feature.home.data.TodoRepository

class GetTodosPageUseCase(
    private val repository: TodoRepository
) {
    suspend operator fun invoke(page: Int, pageSize: Int): AppResult<List<TodoItem>> {
        return repository.getTodos(page = page, pageSize = pageSize)
    }
}
