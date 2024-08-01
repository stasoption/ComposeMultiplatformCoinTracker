import data.sources.CoinTrackerApi
import domain.repository.CoinsRepository

class CoinListRepositoryImpl(private val api: CoinTrackerApi): CoinsRepository {
    override suspend fun getCoins() = api.getCoins()
    override suspend fun getCoinById(coinId: String) = api.getCoinById(coinId)
}