package com.simplez.ctvbox.feature.home.presentation

import com.simplez.ctvbox.core.result.AppError
import com.simplez.ctvbox.core.result.AppResult
import com.simplez.ctvbox.feature.home.data.TodoRepository
import com.simplez.ctvbox.feature.home.domain.GetTodosPageUseCase
import com.simplez.ctvbox.feature.home.domain.TodoItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun initialLoad_success_updatesState() = runTest {
        val fakeRepository = FakeTodoRepository(
            pageResult = mapOf(
                1 to AppResult.Success(createTodos(12, 1))
            )
        )

        val viewModel = HomeViewModel(GetTodosPageUseCase(fakeRepository))

        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertEquals(12, state.todos.items.size)
        assertEquals(2, state.todos.page)
        assertFalse(state.todos.isRefreshing)
        assertFalse(state.todos.endReached)
        assertNull(state.errorMessage)
    }

    @Test
    fun loadNextPage_success_appendsAndMarksEndReached() = runTest {
        val fakeRepository = FakeTodoRepository(
            pageResult = mapOf(
                1 to AppResult.Success(createTodos(12, 1)),
                2 to AppResult.Success(createTodos(3, 13))
            )
        )

        val viewModel = HomeViewModel(GetTodosPageUseCase(fakeRepository))
        advanceUntilIdle()

        viewModel.processIntent(HomeIntent.LoadNextPage)
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertEquals(15, state.todos.items.size)
        assertEquals(3, state.todos.page)
        assertTrue(state.todos.endReached)
        assertFalse(state.todos.isLoadingMore)
        assertNull(state.errorMessage)
    }

    @Test
    fun initialLoad_error_setsErrorAndEmitsEffect() = runTest {
        val fakeRepository = FakeTodoRepository(
            pageResult = mapOf(
                1 to AppResult.Error(AppError.NetworkUnavailable)
            )
        )

        val viewModel = HomeViewModel(GetTodosPageUseCase(fakeRepository))
        val effectDeferred = async { viewModel.effects.first() }

        advanceUntilIdle()

        val state = viewModel.uiState.value
        val effect = effectDeferred.await()
        assertEquals("Network unavailable", state.errorMessage)
        assertTrue(effect is HomeEffect.ShowErrorToast)
        assertEquals("Network unavailable", (effect as HomeEffect.ShowErrorToast).message)
    }

    private fun createTodos(count: Int, startId: Int): List<TodoItem> {
        return (startId until startId + count).map { id ->
            TodoItem(
                id = id,
                title = "todo-$id",
                completed = id % 2 == 0
            )
        }
    }

    private class FakeTodoRepository(
        private val pageResult: Map<Int, AppResult<List<TodoItem>>>
    ) : TodoRepository {

        override suspend fun getTodos(page: Int, pageSize: Int): AppResult<List<TodoItem>> {
            return pageResult[page] ?: AppResult.Success(emptyList())
        }
    }
}
