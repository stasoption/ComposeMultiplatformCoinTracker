package ui.screens.favorites

import EmptyState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ui.theme.DarkGray

@Composable
fun FavoritesScreen() {
    Column(Modifier.fillMaxSize().background(DarkGray)) {
        EmptyState(
            isVisible = true,
            title = "Favorite Crypto Coins",
            message = "Favorite Crypto Coins feature is on its way! Soon, you’ll be able to effortlessly track and manage your top cryptocurrencies all in one place."
        )
    }
}