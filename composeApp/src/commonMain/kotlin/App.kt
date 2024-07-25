
import androidx.compose.runtime.*
import ui.theme.CoinTrackerAppTheme
import ui.screens.list.CoinListScreen

@Composable
fun App() {
    CoinTrackerAppTheme {
        // TODO: Replace with navigation
        // https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-navigation-routing.html#sample-project
        CoinListScreen(onCoinSelected = { coinId -> /*CoinDetailsScreen(coinId)*/ })
    }
}
