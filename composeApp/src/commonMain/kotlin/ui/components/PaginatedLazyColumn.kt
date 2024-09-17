import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import domain.model.Coin
import ui.components.ProgressBar
import ui.screens.list.components.CoinListItem


@Composable
fun PaginatedLazyColumn(
    navController: NavController,
    loadItems: suspend (List<Coin>) -> Boolean,
) {

    val items = remember { mutableStateListOf<Coin>() }
    var isLoading by remember { mutableStateOf(false) }
    var hasMore by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        loadItems(items)
    }

    LazyColumn {
        itemsIndexed(items) { index, item ->
            CoinListItem(item) { coin ->
                navController.navigate("${Screen.Detail.route}/${coin.id}")
            }
            if (index == items.size - 1 && !isLoading && hasMore) {
                LaunchedEffect(Unit) {
                    isLoading = true
                    hasMore = loadItems(items)
                    isLoading = false
                }
            }
        }

        item {
            ProgressBar(isLoading)
        }
    }
}
