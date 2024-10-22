package ui.screens.more

import EmptyState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ui.theme.DarkGray

@Composable
fun MoreScreen() {
    Column(Modifier.fillMaxSize().background(DarkGray)) {
        EmptyState(
            isVisible = true,
            title = "More Screen",
            message = "User Profile screen is on its way, bringing you a more personalized and seamless experience within the app"
        )
    }
}