package ui.components

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
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
import androidx.navigation.NavController
import domain.model.Coin
import kotlinx.coroutines.launch
import ui.screens.list.components.CoinListItem
import ui.theme.ColorAccent
import ui.theme.DarkGray

@Composable
fun CoinListLazyColumn(
    coins: List<Coin>,
    navController: NavController,
) {

    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val isVisible = rememberSaveable { mutableStateOf(false) }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                // Show FAB
                if (available.y < -1) {
                    isVisible.value = true
                }
                // Hide FAB
                if (available.y > 1) {
                    isVisible.value = false
                }

                println("available.y: ${available.y}")
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
                visible = isVisible.value,
                enter = slideInVertically(initialOffsetY = { it * 2 }),
                exit = slideOutVertically(targetOffsetY = { it * 2 }),
            ) {
                FloatingActionButton(
                    contentColor = DarkGray,
                    onClick = { coroutineScope.launch {
                        listState.animateScrollToItem(0)
                        isVisible.value = false
                    }},
                ) {
                    Icon(Icons.AutoMirrored.Filled.List, "Floating action button.")
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
            items(
                count = coins.size,
                key = { coins[it].id },
                itemContent = {
                    CoinListItem(coins[it]) { coin ->
                        navController.navigate("${Screen.Detail.route}/${coin.id}")
                    }
                }
            )
        }

    }
}