import domain.repository.CoinListRepository
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class CoinListRepositoryImpl(val httpClient: HttpClient): CoinListRepository {
    override suspend fun getCoins(): CoinListResponse = httpClient.get(urlString = "assets").body<CoinListResponse>()
}