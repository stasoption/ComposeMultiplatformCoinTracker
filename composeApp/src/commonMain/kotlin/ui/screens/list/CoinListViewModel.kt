import data.sources.dto.toCoin
import domain.model.Coin
import domain.repository.CoinsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ui.base.BaseViewModel

data class CoinListUiState(
    val coins: List<Coin> = emptyList()
)

class CoinListViewModel constructor(private val coinsRepository: CoinsRepository) : BaseViewModel() {
    private val _uiState = MutableStateFlow(CoinListUiState(emptyList()))
    
    val uiState = _uiState.asStateFlow()
    
    fun updateCoins() = launchIO {
        _uiState.update { it.copy(coins = coinsRepository.getCoins().map { dto -> dto.toCoin() }) }
    }
}