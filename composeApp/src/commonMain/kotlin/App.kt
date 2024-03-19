
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ui.screens.list.CoinListScreen

@Composable
fun CoinTrackerAppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = MaterialTheme.colors.copy(primary = Color.Blue, secondary = Color.Yellow),
        shapes = MaterialTheme.shapes.copy(
            small = RoundedCornerShape(0.dp),
            medium = RoundedCornerShape(0.dp),
            large = RoundedCornerShape(0.dp)
        )
    ) {
        content()
    }
}

@Composable
fun App() {
    CoinTrackerAppTheme {
        CoinListScreen(onCoinSelected = { coinId -> println("coinId=$coinId") })
    }
}
