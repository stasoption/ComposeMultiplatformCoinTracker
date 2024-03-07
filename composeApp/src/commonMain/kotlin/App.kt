import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory

@Composable
fun BirdAppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = MaterialTheme.colors.copy(primary = Color.Black),
        shapes = MaterialTheme.shapes.copy(
            small = RoundedCornerShape(0.dp),
            medium = RoundedCornerShape(0.dp),
            large = RoundedCornerShape(0.dp)
        )
    ) {
        content()
    }
}

@Composable
fun App() {
    BirdAppTheme {
        val coinListViewModel = getViewModel(Unit, viewModelFactory { CoinListViewModel() })
        val uiState by coinListViewModel.uiState.collectAsState()
        LaunchedEffect(coinListViewModel) { coinListViewModel.updateCoins() }
        CoinListScreen(uiState, onCoinSelected = {  })
    }
}

@Composable
fun CoinListScreen(uiState: CoinListUiState, onCoinSelected: (id: String) -> Unit) {
    println("${uiState.coins.toString()}")
}