package domain.use_case

import data.sources.dto.toCoinDetail
import domain.ServerResponse
import domain.model.CoinDetail
import domain.repository.CoinsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCoinByIdUseCase(private val repository: CoinsRepository) {
    operator fun invoke(coinId: String): Flow<ServerResponse<CoinDetail>> = flow {
        try {
            emit(ServerResponse.Loading())
            val coin = repository.getCoinById(coinId).toCoinDetail()
            emit(ServerResponse.Success(coin))
        } catch(e: Exception) {
            emit(ServerResponse.Error(e.message ?: "Unexpected error happened"))
        }
    }
}