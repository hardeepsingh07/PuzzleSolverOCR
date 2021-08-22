package com.hardeepsingh.puzzlesolverocr.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

abstract class BaseActivity<Event: BaseEvent, State: BaseState, Effect: BaseEffect>(): AppCompatActivity() {

    abstract val processor: BaseProcessor<Event, State, Effect>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch { processor.effects.collect { handleEffect(it) } }
        onViewCreated()
    }

    @Composable
    fun provideUIState() = processor.states.collectAsState().value

    fun emit(event: Event) { lifecycleScope.launch { processor.emitEvent(event) } }

    abstract fun onViewCreated()
    open fun handleEffect(effect: Effect) {}
}