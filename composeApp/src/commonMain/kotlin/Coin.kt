import kotlinx.serialization.Serializable

@Serializable
data class CoinListResponse(
    val timestamp: Long,
    val data: List<Coin>
)

@Serializable
data class Coin(
    val id: String,
    val rank: String?,
    val symbol: String?,
    val name: String?,
    val supply: String?,
    val maxSupply: String?,
    val marketCapUsd: String?,
    val volumeUsd24Hr: String?,
    val priceUsd: String?,
    val changePercent24Hr: String?,
    val vwap24Hr: String?,
    val explorer: String?
)