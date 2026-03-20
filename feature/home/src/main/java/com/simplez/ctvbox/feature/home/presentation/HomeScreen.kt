package com.simplez.ctvbox.feature.home.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.simplez.ctvbox.core.mvi.PaginationState
import com.simplez.ctvbox.feature.home.domain.TodoItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(viewModel) {
        viewModel.effects.collect { effect ->
            when (effect) {
                is HomeEffect.ShowErrorToast -> {
                    Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    HomeScreen(
        state = uiState,
        onRefresh = { viewModel.processIntent(HomeIntent.Refresh) },
        onLoadMore = { viewModel.processIntent(HomeIntent.LoadNextPage) }
    )
}

@Composable
fun HomeScreen(
    state: HomeState,
    onRefresh: () -> Unit,
    onLoadMore: () -> Unit
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .testTag("home_root"),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "MVI + Koin + Ktor",
                    style = MaterialTheme.typography.titleLarge
                )
                OutlinedButton(onClick = onRefresh, enabled = !state.todos.isRefreshing) {
                    Text(text = "Refresh")
                }
            }

            state.errorMessage?.let {
                Text(
                    text = "Error: $it",
                    modifier = Modifier.testTag("home_error"),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.error
                )
            }

            if (state.todos.isRefreshing && state.todos.items.isEmpty()) {
                CircularProgressIndicator(modifier = Modifier.testTag("home_loading"))
            } else {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .testTag("home_list"),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(
                        items = state.todos.items,
                        key = { it.id }
                    ) { todo ->
                        TodoItemRow(todo = todo)
                    }

                    item {
                        when {
                            state.todos.isLoadingMore -> {
                                CircularProgressIndicator(
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .testTag("home_loading_more")
                                )
                            }

                            state.todos.endReached -> {
                                Text(
                                    text = "No more data",
                                    modifier = Modifier.testTag("home_no_more"),
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }

                            else -> {
                                Button(
                                    onClick = onLoadMore,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .testTag("home_load_more")
                                ) {
                                    Text(text = "Load More")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun TodoItemRow(todo: TodoItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "#${todo.id} ${todo.title}",
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = if (todo.completed) "Done" else "Todo",
            color = if (todo.completed) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    MaterialTheme {
        HomeScreen(
            state = HomeState(
                todos = PaginationState(
                    items = listOf(
                        TodoItem(1, "delectus aut autem", false),
                        TodoItem(2, "quis ut nam facilis et officia qui", true)
                    ),
                    page = 2,
                    pageSize = 12
                )
            ),
            onRefresh = {},
            onLoadMore = {}
        )
    }
}
