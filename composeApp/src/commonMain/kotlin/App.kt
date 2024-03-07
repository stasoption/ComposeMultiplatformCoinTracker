import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory

@Composable
fun BirdAppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = MaterialTheme.colors.copy(primary = Color.Black),
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
    BirdAppTheme {
        val coinListViewModel = getViewModel(Unit, viewModelFactory { CoinListViewModel() })
        val uiState by coinListViewModel.uiState.collectAsState()
        LaunchedEffect(coinListViewModel) { coinListViewModel.updateCoins() }
        CoinListScreen(uiState, onCoinSelected = { coinId -> println("coinId=$coinId") })
    }
}

@Composable
fun CoinListScreen(uiState: CoinListUiState, onCoinSelected: (id: String) -> Unit) {
    println("${uiState.coins.toString()}")

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {

        Text(text = "CoinTracker")

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
