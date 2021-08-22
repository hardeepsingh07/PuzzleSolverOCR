package com.hardeepsingh.puzzlesolverocr.view

import com.hardeepsingh.puzzlesolverocr.base.BaseProcessor
import kotlinx.coroutines.delay


class MainProcessor: BaseProcessor<MainEvent, MainState, MainEffect>() {

    override fun initialEvent() = MainEvent.NoOp
    override fun initialState() = MainState.Uninitialized
    override fun initialEffect() = MainEffect.NoOp

    override suspend fun handleEvent(event: MainEvent) {
        when(event) {
            MainEvent.Initialize -> if(currentEvent == MainState.Uninitialized) {
                emitState(MainState.Loading)
                delay(5000)
                emitState(MainState.Data)
            }
        }
    }
}