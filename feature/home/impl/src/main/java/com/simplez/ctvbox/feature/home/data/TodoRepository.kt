package com.simplez.ctvbox.feature.home.data

import com.simplez.ctvbox.core.network.NetworkErrorMapper
import com.simplez.ctvbox.core.result.AppResult
import com.simplez.ctvbox.feature.home.domain.TodoItem
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.serialization.Serializable

interface TodoRepository {
    suspend fun getTodos(page: Int, pageSize: Int): AppResult<List<TodoItem>>
}

class TodoRepositoryImpl(
    private val httpClient: HttpClient,
    private val errorMapper: NetworkErrorMapper
) : TodoRepository {

    override suspend fun getTodos(page: Int, pageSize: Int): AppResult<List<TodoItem>> {
        return try {
            val response: List<TodoDto> = httpClient.get("https://jsonplaceholder.typicode.com/todos") {
                parameter("_page", page)
                parameter("_limit", pageSize)
            }.body()
            AppResult.Success(
                response.map {
                    TodoItem(
                        id = it.id,
                        title = it.title,
                        completed = it.completed
                    )
                }
            )
        } catch (throwable: Throwable) {
            AppResult.Error(errorMapper.map(throwable))
        }
    }
}

@Serializable
private data class TodoDto(
    val id: Int,
    val title: String,
    val completed: Boolean
)
