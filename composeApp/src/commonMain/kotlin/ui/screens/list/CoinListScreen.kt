package ui.screens.list

import CoinListViewModel
import ErrorText
import SearchPanel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import cointracker.composeapp.generated.resources.Res
import cointracker.composeapp.generated.resources.app_name
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import ui.screens.list.components.CoinListLazyColumn
import ui.components.ProgressBar
import ui.theme.DarkGray
import ui.theme.TextPrimary

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
            },
            contentColor = TextPrimary,
            actions = {
                IconButton(onClick = { /* Handle action click */ }) {
                    Icon(Icons.Filled.Menu, contentDescription = "Search")
                }
            }

        )

        SearchPanel(
            onSearchTextChange = { viewModel.searchQuery = it },
            onSearchClear = { viewModel.searchQuery = "" }
        )

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            CoinListLazyColumn(
                coins = uiState.coins,
                isLoading = uiState.isLoading,
                navController = navController
            )
            ErrorText(uiState.error)
//            ProgressBar(uiState.isLoading)
        }
    }
}
