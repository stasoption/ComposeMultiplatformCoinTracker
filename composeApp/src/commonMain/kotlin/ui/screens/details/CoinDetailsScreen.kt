package ui.screens.details

import CoinDetailsViewModel
import ErrorText
import androidx.compose.foundation.Image
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import BigPadding
import Logotype
import MediumPadding
import SmallPadding
import cointracker.composeapp.generated.resources.Res
import cointracker.composeapp.generated.resources.app_name
import cointracker.composeapp.generated.resources.compose_multiplatform
import cointracker.composeapp.generated.resources.description
import cointracker.composeapp.generated.resources.tags
import io.github.aakira.napier.Napier
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import ui.components.ProgressBar
import ui.screens.details.components.CoinTagItem
import ui.theme.DarkGray
import ui.theme.TextPrimary
import ui.theme.TextSecondary

@OptIn(ExperimentalResourceApi::class, ExperimentalLayoutApi::class)
@Composable
fun CoinDetailsScreen(
    coinId: String,
    navController: NavController,
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
                    onClick = { navController.popBackStack() }
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
                    /* Title area */
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.compose_multiplatform),
                            contentDescription = "Empty state image",
                            modifier = Modifier.width(Logotype).height(Logotype)
                        )
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = buildAnnotatedString {
                                append(uiState.coin?.name.orEmpty())
                                pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
                                append(" (${uiState.coin?.symbol.orEmpty()})")
                                pop()
                            },
                            color = TextPrimary,
                            style = MaterialTheme.typography.h1,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                    Spacer(modifier = Modifier.height(BigPadding))

                    /* Description area */
                    Text(
                        text = stringResource(Res.string.description),
                        color = TextPrimary,
                        style = MaterialTheme.typography.h3
                    )
                    Spacer(modifier = Modifier.height(SmallPadding))
                    Text(
                        text = "${uiState.coin?.description}",
                        color = TextSecondary,
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight.Normal
                    )
                    Spacer(modifier = Modifier.height(MediumPadding))

                    /* Tags area */
                    Text(
                        text = stringResource(Res.string.tags),
                        color = TextPrimary,
                        style = MaterialTheme.typography.h3
                    )
                    Spacer(modifier = Modifier.height(SmallPadding))
                    FlowRow(modifier = Modifier.fillMaxWidth()) {
                        uiState.coin?.tags?.forEach { tag -> CoinTagItem(tag = tag) }
                    }
                }
            }

            ErrorText(uiState.error)
            ProgressBar(uiState.isLoading)
        }
    }
}
