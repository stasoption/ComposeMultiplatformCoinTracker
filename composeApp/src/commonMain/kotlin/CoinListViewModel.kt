import dev.icerock.moko.mvvm.viewmodel.ViewModel
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.websocket.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class CoinListUiState(
    val coins: List<Coin> = emptyList()
)

class CoinListViewModel: ViewModel() {
    
    private val httpClient = HttpClient() { install(ContentNegotiation) { json() } }

    private val _uiState = MutableStateFlow(CoinListUiState(emptyList()))
    
    private var isUpdating: Boolean = true

    val uiState = _uiState.asStateFlow()
    
    override fun onCleared() {
        isUpdating = false
        httpClient.close()
    }
    
    fun updateCoins() = viewModelScope.launch {
        while (isUpdating) {
            _uiState.update {
                val items = getCoins().data
                println("${items.map { it.priceUsd }.take(5)}")
                it.copy(coins = items)
            }
            delay(1000L)
        }
    }
    
    private suspend fun getCoins(): CoinListResponse = httpClient
        .get("https://api.coincap.io/v2/assets")
        .body<CoinListResponse>()
}