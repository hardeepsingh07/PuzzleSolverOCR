package com.hardeepsingh.puzzlesolverocr.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

abstract class BaseProcessor<Event: BaseEvent, State: BaseState, Effect: BaseEffect>(): ViewModel() {

    private val events = MutableStateFlow(initialEvent())
    val states = MutableStateFlow(initialState())
    val effects = MutableStateFlow(initialEffect())
    val currentEvent = states.value

    init {
        viewModelScope.launch { events.collect { handleEvent(it) } }
    }

    suspend fun emitEvent(event: Event) { events.emit(event) }
    protected suspend fun emitEffect(effect: Effect) { effects.emit(effect) }
    protected suspend fun emitState(state: State) { states.emit(state) }

    abstract fun initialEvent(): Event
    abstract fun initialState(): State
    abstract fun initialEffect(): Effect
    abstract suspend fun handleEvent(event: Event)
}

