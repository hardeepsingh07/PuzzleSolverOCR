package com.hardeepsingh.puzzlesolverocr.view

import com.hardeepsingh.puzzlesolverocr.base.BaseEffect
import com.hardeepsingh.puzzlesolverocr.base.BaseEvent
import com.hardeepsingh.puzzlesolverocr.base.BaseState

sealed class MainEvent: BaseEvent() {
    object NoOp: MainEvent()
    object Initialize: MainEvent()
}

sealed class MainState: BaseState() {
    object Uninitialized: MainState()
    object Loading: MainState()
    object Data: MainState()
}

sealed class MainEffect: BaseEffect() {
    object NoOp: MainEffect()
}