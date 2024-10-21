package ui.screens.list.components

import Platform
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import cointracker.composeapp.generated.resources.Res
import cointracker.composeapp.generated.resources.arrow_upward
import domain.model.Coin
import io.github.aakira.napier.Napier
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import ui.theme.ColorAccent
import ui.theme.DarkGray

@OptIn(ExperimentalResourceApi::class)
@Composable
fun CoinListLazyColumn(
    coins: List<Coin>,
    isLoading: Boolean,
    onNavigateNext: (coinId: String) -> Unit
) {

    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val isFabVisible = rememberSaveable { mutableStateOf(false) }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                // Show FAB
                if (available.y < -1) {
                    isFabVisible.value = true
                }
                // Hide FAB
                if (available.y > 1) {
                    isFabVisible.value = false
                }
                return Offset.Zero
            }
        }
    }

    Scaffold(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp),
        backgroundColor = DarkGray,
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            AnimatedVisibility(
                visible = isFabVisible.value,
                enter = slideInVertically(initialOffsetY = { it * 2 }),
                exit = slideOutVertically(targetOffsetY = { it * 2 }),
            ) {
                FloatingActionButton(
                    contentColor = DarkGray,
                    onClick = { coroutineScope.launch {
                        listState.scrollToItem(0)
                        isFabVisible.value = false
                    }},
                ) {
                    Icon(painterResource(Res.drawable.arrow_upward), "Scroll to top button FAB.")
                }
            }
        }
    ) { innerPadding ->
        LazyColumn(
            state = listState,
            modifier = Modifier
                .padding(innerPadding)
                .background(ColorAccent)
                .nestedScroll(nestedScrollConnection),
            verticalArrangement = Arrangement.spacedBy(0.5.dp)
        ) {
            Napier.d(tag = "Coin List:", message = coins.joinToString(separator = ",") { it.symbol })
            items(
                count = if (isLoading) 10 else coins.size,
                key = { if (isLoading) Platform().randomUUID else coins[it].id },
                itemContent = {
                    if (isLoading) {
                        CoinShimmerItem()
                    } else {
                        CoinListItem(coins[it]) { coin ->
                            onNavigateNext.invoke(coin.id)
                        }
                    }
                }
            )
        }
    }
}