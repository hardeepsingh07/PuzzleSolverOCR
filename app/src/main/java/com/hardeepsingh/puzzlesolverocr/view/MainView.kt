package com.hardeepsingh.puzzlesolverocr.view

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.hardeepsingh.puzzlesolverocr.base.CenterColumn


@Composable
fun MainView(state: MainState) {
    when(state) {
        MainState.Data -> MainContent(state = state)
        MainState.Loading -> MainLoading()
    }
}

@Composable
fun MainContent(state: MainState) {
    CenterColumn {
        Text(text = "Hello")
    }
}

@Composable
fun MainLoading() {
    CenterColumn {
        CircularProgressIndicator()
    }
}