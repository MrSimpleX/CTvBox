package com.simplez.ctvbox.core.mvi

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update

abstract class BaseMviViewModel<Intent : Any, State : Any, Effect : Any>(
    initialState: State
) : ViewModel() {

    private val _uiState = MutableStateFlow(initialState)
    val uiState: StateFlow<State> = _uiState.asStateFlow()

    private val _effects = Channel<Effect>(Channel.BUFFERED)
    val effects = _effects.receiveAsFlow()

    fun processIntent(intent: Intent) {
        onIntent(intent)
    }

    protected abstract fun onIntent(intent: Intent)

    protected fun updateState(reducer: State.() -> State) {
        _uiState.update(reducer)
    }

    protected suspend fun postEffect(effect: Effect) {
        _effects.send(effect)
    }
}
