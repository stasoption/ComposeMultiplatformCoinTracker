package ui.screens.list

import Coin
import CoinListUiState
import CoinListViewModel
import DownPercentIndicator
import UpPercentIndicator
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.compose.koinInject

@Composable
fun CoinListScreen(
    viewModel: CoinListViewModel = koinInject(),
    onCoinSelected: (id: String) -> Unit
) {
    val uiState = viewModel.uiState.collectAsState().value
    LaunchedEffect(viewModel) { viewModel.updateCoins() }
    
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
