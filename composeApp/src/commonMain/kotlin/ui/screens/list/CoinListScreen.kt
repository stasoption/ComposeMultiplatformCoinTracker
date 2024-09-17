package ui.screens.list

import CoinListViewModel
import ErrorText
import SearchPanel
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
import androidx.navigation.NavController
import cointracker.composeapp.generated.resources.Res
import cointracker.composeapp.generated.resources.app_name
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import ui.components.ProgressBar
import ui.screens.list.components.CoinListItem
import ui.theme.ColorAccent
import ui.theme.DarkGray

@OptIn(ExperimentalResourceApi::class)
@Composable
fun CoinListScreen(
    navController: NavController,
    viewModel: CoinListViewModel = koinInject(),
) {
    val uiState = viewModel.uiState.collectAsState().value
    LaunchedEffect(viewModel) { viewModel.updateCoins() }

    Column(
        Modifier.fillMaxSize().background(DarkGray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {

        TopAppBar(
            title = {
                Text(
                    text = stringResource(Res.string.app_name),
                    style = MaterialTheme.typography.h3,
                )
            }
        )

        SearchPanel(
            onSearchTextChange = { viewModel.searchQuery = it },
            onSearchClear = { viewModel.searchQuery = "" }
        )

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .background(ColorAccent),
                verticalArrangement = Arrangement.spacedBy(0.5.dp)
            ) {
                items(
                    count = uiState.coins.size,
                    itemContent = {
                        CoinListItem(uiState.coins[it]) { coin ->
                            navController.navigate("${Screen.Detail.route}/${coin.id}")
                        }
                    },
                )
            }

            ErrorText(uiState.error)
            ProgressBar(uiState.isLoading)
        }
    }
}
