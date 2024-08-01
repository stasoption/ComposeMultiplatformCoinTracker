package data.sources

import data.sources.dto.CoinDetailDto
import data.sources.dto.CoinDto
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Path
import domain.Constants
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

interface CoinTrackerApi {
    @GET("/v1/coins")
    suspend fun getCoins(): List<CoinDto>
    @GET("/v1/coins/{coinId}")
    suspend fun getCoinById(@Path("coinId") coinId: String): CoinDetailDto
}

class CoinTrackerApiImpl(private val httpClient: HttpClient) : CoinTrackerApi {
    override suspend fun getCoins() = httpClient.get(urlString = Constants.PARAM_COINS).body<List<CoinDto>>()
    override suspend fun getCoinById(coinId: String) = httpClient.get(urlString = Constants.PARAM_COINS + "/$coinId").body<CoinDetailDto>()
}