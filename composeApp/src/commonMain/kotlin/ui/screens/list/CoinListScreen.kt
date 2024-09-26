package ui.screens.list

import CoinListViewModel
import EmptyState
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
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import cointracker.composeapp.generated.resources.Res
import cointracker.composeapp.generated.resources.app_name
import cointracker.composeapp.generated.resources.empty_search_result_message
import cointracker.composeapp.generated.resources.empty_state_message
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import ui.screens.list.components.CoinListLazyColumn
import ui.screens.list.components.CoinListScreenMenu
import ui.theme.DarkGray
import ui.theme.TextPrimary

@OptIn(ExperimentalResourceApi::class)
@Composable
fun CoinListScreen(
    navController: NavController,
    viewModel: CoinListViewModel = koinInject(),
) {
    var menuExpanded by remember { mutableStateOf(false) }
    val uiState = viewModel.uiState.collectAsState().value
    LaunchedEffect(viewModel) { viewModel.updateCoins() }

    Column(Modifier.fillMaxSize().background(DarkGray)) {
        /* Toolbar */
        TopAppBar(
            title = {
                Text(
                    text = stringResource(Res.string.app_name),
                    style = MaterialTheme.typography.h3,
                )
            },
            contentColor = TextPrimary,
            actions = {
                IconButton(onClick = { menuExpanded = true }) {
                    Icon(Icons.Filled.Menu, contentDescription = "SearchButton")
                }
            }
        )
        /* DropdownMenu */
        CoinListScreenMenu(menuExpanded) {
            menuExpanded = false
        }
        /* SearchPanel */
        SearchPanel(
            onSearchTextChange = { viewModel.searchQuery = it },
            onSearchClear = { viewModel.searchQuery = "" }
        )
        /* Coin's list + EmptyState + ErrorText */
        Box(modifier = Modifier.fillMaxSize()) {
            CoinListLazyColumn(
                coins = uiState.coins,
                isLoading = uiState.isLoading,
                navController = navController
            )
            EmptyState(
                isVisible = uiState.isLoading.not() && uiState.coins.isEmpty(),
                message = if (viewModel.searchQuery.isNotEmpty()) {
                    stringResource(Res.string.empty_search_result_message)
                } else {
                    stringResource(Res.string.empty_state_message)
                }
            )
            ErrorText(uiState.error)
        }
    }
}
