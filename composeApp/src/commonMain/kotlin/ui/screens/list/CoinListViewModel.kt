import domain.ServerResponse
import domain.model.Coin
import domain.use_case.GetCoinsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import ui.base.BaseViewModel

data class CoinListUiState(
    val coins: List<Coin> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)

class CoinListViewModel(private val getCoinsUseCase: GetCoinsUseCase) : BaseViewModel() {

    private val _uiState = MutableStateFlow(CoinListUiState(emptyList()))
    val uiState = _uiState.asStateFlow()

    var searchQuery: String = ""
        set(value) {
            field = value
            // TODO -> update coins list
        }

    fun updateCoins() = launchIO {
        getCoinsUseCase().collectLatest { result ->
            when (result) {
                is ServerResponse.Success -> {
                    _uiState.value = CoinListUiState(
                        coins = result.data?.filter { it.filterBySearchQuery() } ?: emptyList()
                    )
                }
                is ServerResponse.Error -> {
                    _uiState.value = CoinListUiState(
                        error = result.message ?: "Unexpected error happened"
                    )
                }
                is ServerResponse.Loading -> {
                    _uiState.value = CoinListUiState(isLoading = true)
                }
            }
        }
    }

    private fun Coin.filterBySearchQuery(): Boolean {
        if (searchQuery.isEmpty()) return true
        return this.name.lowercase().contains(searchQuery.lowercase()) ||
            this.symbol.lowercase().contains(searchQuery.lowercase())
    }
}