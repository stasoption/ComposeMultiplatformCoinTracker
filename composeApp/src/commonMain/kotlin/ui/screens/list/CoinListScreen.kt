package ui.screens.list

import CoinListViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.compose.koinInject
import ui.screens.list.components.CoinListItem

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
                )},
            modifier = Modifier.background(MaterialTheme.colors.primary)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(1.dp)
        ) {
            items(
                count = uiState.coins.size,
                itemContent = {
                    CoinListItem(uiState.coins[it]) { coin ->
                        onCoinSelected(coin.id)
                    }
                },
            )
        }
    }
}
