package com.hardeepsingh.puzzlesolverocr.base

import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.hardeepsingh.puzzlesolverocr.ui.theme.PuzzleSolverOCRTheme

fun AppCompatActivity.setContentView(content: @Composable () -> Unit) {
    setContent { PuzzleSolverOCRTheme { Surface(color = MaterialTheme.colors.background) { content() } } }
}

@Composable
fun CenterColumn(content: @Composable () -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        content()
    }
}