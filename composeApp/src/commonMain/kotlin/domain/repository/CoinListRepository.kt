package domain.repository

import CoinListResponse

interface CoinListRepository {
    suspend fun getCoins(): CoinListResponse
}
