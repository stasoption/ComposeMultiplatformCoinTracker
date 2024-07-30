import domain.ServerResponse
import domain.model.CoinDetail
import domain.use_case.GetCoinByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import ui.base.BaseViewModel

data class CoinDetailsUiState(
    val coin: CoinDetail? = null,
    val isLoading: Boolean = false,
    val error: String = ""
)

class CoinDetailsViewModel(
    private val getCoinByIdUseCase: GetCoinByIdUseCase
) : BaseViewModel() {
    private val _uiState = MutableStateFlow(CoinDetailsUiState())
    val uiState = _uiState.asStateFlow()

    fun getCoin(coinId: String) = launchIO {
        getCoinByIdUseCase(coinId).collectLatest { result ->
            when (result) {
                is ServerResponse.Success -> {
                    _uiState.value = CoinDetailsUiState(coin = result.data)
                }
                is ServerResponse.Error -> {
                    _uiState.value = CoinDetailsUiState(
                        error = result.message ?: "Unexpected error happened"
                    )
                }
                is ServerResponse.Loading -> {
                    _uiState.value = CoinDetailsUiState(isLoading = true)
                }
            }
        }
    }
}