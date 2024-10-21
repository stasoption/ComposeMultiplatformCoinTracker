package ui.screens.details

import CoinDetailsViewModel
import ErrorText
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import CoinDetailsPlaceholder
import CoinDetailsSheet
import MediumPadding
import cointracker.composeapp.generated.resources.Res
import cointracker.composeapp.generated.resources.app_name
import io.github.aakira.napier.Napier
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import ui.components.ProgressBar
import ui.theme.DarkGray
import ui.theme.TextPrimary

@OptIn(ExperimentalResourceApi::class)
@Composable
fun CoinDetailsScreen(
    coinId: String,
    onBackClick: () -> Unit,
    viewModel: CoinDetailsViewModel = koinInject()
) {
    val uiState = viewModel.uiState.collectAsState().value
    LaunchedEffect(viewModel) { viewModel.getCoin(coinId) }

    Napier.d(tag = "Coin Details:", message = "${uiState.coin?.linksExtended?.map { "\n$it" }}")

    Column(
        Modifier.fillMaxSize().background(DarkGray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        TopAppBar(
            title = {
                Text(
                    text = uiState.coin?.name ?: stringResource(Res.string.app_name),
                    style = MaterialTheme.typography.h3,
                )
            },
            contentColor = TextPrimary,
            navigationIcon = {
                IconButton(
                    onClick = { onBackClick.invoke() }
                ) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Menu")
                }
            }
        )

        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = MediumPadding)
            ) {
                item {
                    if (uiState.isLoading) {
                        CoinDetailsPlaceholder()
                    } else {
                        uiState.coin?.let { CoinDetailsSheet(it) }
                    }
                }
            }

            ErrorText(uiState.error)
            ProgressBar(uiState.isLoading)
        }
    }
}
