import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import org.jetbrains.compose.ui.tooling.preview.Preview

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
        val coinListViewModel = getViewModel(Unit, viewModelFactory { CoinListViewModel() })
        val uiState by coinListViewModel.uiState.collectAsState()
        LaunchedEffect(coinListViewModel) { coinListViewModel.updateCoins() }
        CoinListScreen(uiState, onCoinSelected = { coinId -> println("coinId=$coinId") })
    }
}

@Composable
fun CoinListScreen(uiState: CoinListUiState, onCoinSelected: (id: String) -> Unit) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {

        TopAppBar(
            title = {
                Text(
                    text = "CoinTracker",
                    color = MaterialTheme.colors.secondary,
                    fontSize = 18.sp
                )
            },
            modifier = Modifier.background(MaterialTheme.colors.primary)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp)
        ) {
            items(
                count = uiState.coins.size,
                itemContent = { CoinItem(uiState.coins[it]) }
            )
        }
    }
}

@Composable
fun CoinItem(coin: Coin) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
        ) {
        Text(text = "${coin.name}: $${coin.priceUsd}")
    }
}

@Preview
@Composable
private fun CoinListScreenPreview() {
    CoinListScreen(CoinListUiState(listOf())) {}
}
