package ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ProgressBar(isVisible: Boolean, modifier: Modifier = Modifier.fillMaxSize()) {
    Box(modifier = modifier) {
        val visibility = rememberSaveable { mutableStateOf(isVisible) }
        visibility.value = isVisible
        AnimatedVisibility(visible = isVisible, modifier = Modifier.align(Alignment.Center)) {
            CircularProgressIndicator(color = MaterialTheme.colors.secondary, )
        }
    }
}