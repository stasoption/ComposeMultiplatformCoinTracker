
import androidx.compose.runtime.*
import ui.theme.CoinTrackerAppTheme
import ui.screens.list.CoinListScreen

@Composable
fun App() {
    CoinTrackerAppTheme {
        CoinListScreen(onCoinSelected = { coinId -> println("coinId=$coinId") })
    }
}
