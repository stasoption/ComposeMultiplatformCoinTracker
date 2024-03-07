import dev.icerock.moko.mvvm.viewmodel.ViewModel
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class CoinListUiState(
    val coins: List<Coin> = emptyList()
)

class CoinListViewModel: ViewModel() {
    
    private val _uiState = MutableStateFlow(CoinListUiState(emptyList()))
    
    val uiState = _uiState.asStateFlow()

    private val httpClient = HttpClient() {
        install(ContentNegotiation) {
            json()
        }
    }
    
    override fun onCleared() {
        httpClient.close()
    }
    
    fun updateCoins() = viewModelScope.launch {
        _uiState.update {
            it.copy(coins = getCoins().data)
        }
    }
    
    private suspend fun getCoins(): CoinListResponse =
        httpClient
            .get("https://api.coincap.io/v2/assets")
            .body<CoinListResponse>()
}