package ui.screens.details

import CoinDetailsViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cointracker.composeapp.generated.resources.Res
import cointracker.composeapp.generated.resources.app_name
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

@OptIn(ExperimentalResourceApi::class)
@Composable
fun CoinDetailsScreen(
    coinId: String,
    viewModel: CoinDetailsViewModel = koinInject()
) {
    val uiState = viewModel.uiState.collectAsState().value
    LaunchedEffect(viewModel) { viewModel.getCoin(coinId) }

    Column(
        Modifier.fillMaxSize().background(MaterialTheme.colors.secondary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {

        TopAppBar(
            title = {
                Text(
                    text = uiState.coin?.name ?: stringResource(Res.string.app_name),
                    style = MaterialTheme.typography.h3,
                )
            }
        )

        Box(modifier = Modifier.fillMaxSize()) {

            Text(
                text = uiState.coin?.description ?: "",
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .align(Alignment.Center)
            )
            
            if (uiState.error.isNotBlank()) {
                Text(
                    text = uiState.error,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .align(Alignment.Center)
                )
            }

            if (uiState.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}
