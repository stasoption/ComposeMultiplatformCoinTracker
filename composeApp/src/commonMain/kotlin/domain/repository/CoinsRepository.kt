package domain.repository

import data.sources.dto.CoinDetailDto
import data.sources.dto.CoinDto


interface CoinsRepository {
    suspend fun getCoins(): List<CoinDto>
    suspend fun getCoinById(coinId: String): CoinDetailDto
}
