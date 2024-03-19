import domain.repository.CoinListRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ui.base.BaseViewModel

data class CoinListUiState(
    val coins: List<Coin> = emptyList()
)

class CoinListViewModel constructor(private val coinListRepository: CoinListRepository) : BaseViewModel() {
    private val _uiState = MutableStateFlow(CoinListUiState(emptyList()))
    
    val uiState = _uiState.asStateFlow()
    
    fun updateCoins() = launchIO {
        _uiState.update { it.copy(coins = coinListRepository.getCoins().data) }
    }
}