package domain.use_case

import data.sources.dto.toCoin
import domain.ServerResponse
import domain.model.Coin
import domain.repository.CoinsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCoinsUseCase(private val repository: CoinsRepository) {
    operator fun invoke(): Flow<ServerResponse<List<Coin>>> = flow {
        try {
            emit(ServerResponse.Loading())
            val coins = repository.getCoins().filter { it.isActive }.map { it.toCoin() }
            emit(ServerResponse.Success(coins))
        } catch(e: Exception) {
            emit(ServerResponse.Error(e.message ?: "Unexpected error happened"))
        }
    }
}