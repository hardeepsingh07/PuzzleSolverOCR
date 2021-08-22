package com.hardeepsingh.puzzlesolverocr.view

import com.hardeepsingh.puzzlesolverocr.base.BaseActivity
import com.hardeepsingh.puzzlesolverocr.base.setContentView

class MainActivity : BaseActivity<MainEvent, MainState, MainEffect>() {

    override val processor = MainProcessor()

    override fun onViewCreated() {
        setContentView {
            MainView(state = provideUIState()).also { emit(MainEvent.Initialize) }
        }
    }

    override fun handleEffect(effect: MainEffect) {

    }
}
