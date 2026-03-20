package com.simplez.ctvbox.feature.home.di

import com.simplez.ctvbox.feature.home.data.TodoRepository
import com.simplez.ctvbox.feature.home.data.TodoRepositoryImpl
import com.simplez.ctvbox.feature.home.domain.GetTodosPageUseCase
import com.simplez.ctvbox.feature.home.presentation.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val homeModule = module {
    single<TodoRepository> { TodoRepositoryImpl(get(), get()) }
    factory { GetTodosPageUseCase(get()) }
    viewModelOf(::HomeViewModel)
}
