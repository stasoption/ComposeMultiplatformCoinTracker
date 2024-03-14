import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontWeight
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

        LazyColumn( verticalArrangement = Arrangement.spacedBy(1.dp)) {
            items(
                count = uiState.coins.size,
                itemContent = { CoinItem(uiState.coins[it]) },
            )
        }
    }
}

@Composable
fun CoinItem(coin: Coin) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        content = {
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp, horizontal = 16.dp),
                verticalAlignment = CenterVertically
            ) {
                Text(
                    text = "${coin.name}",
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                )

                Text(
                    text = " (${coin.symbol})",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Normal
                )

                Spacer(modifier = Modifier.weight(1f))

                val indicator = if (coin.isPercent24HrUp) UpPercentIndicator() else DownPercentIndicator()
                val indicatorColor = if (coin.isPercent24HrUp) Color.Green else Color.Red
                Box(modifier = Modifier.size(16.dp).clip(shape = indicator).background(indicatorColor))

                Text(
                    text = " ${coin.formattedPercent}%",
                    fontSize = 14.sp,
                    color = indicatorColor,
                    fontWeight = FontWeight.Normal
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "$${coin.formattedPrice}",
                    fontSize = 14.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    )
}

@Preview
@Composable
private fun CoinListScreenPreview() {
    CoinListScreen(CoinListUiState(listOf())) {}
}
