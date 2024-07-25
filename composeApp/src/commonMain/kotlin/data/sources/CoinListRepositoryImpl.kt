import data.sources.dto.CoinDetailDto
import data.sources.dto.CoinDto
import domain.Constants
import domain.repository.CoinsRepository
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class CoinListRepositoryImpl(val httpClient: HttpClient): CoinsRepository {

    override suspend fun getCoins() = httpClient.get(urlString = Constants.PARAM_COINS).body<List<CoinDto>>()
    override suspend fun getCoinById(coinId: String) = httpClient.get(urlString = Constants.PARAM_COINS + "/$coinId").body<CoinDetailDto>()
}