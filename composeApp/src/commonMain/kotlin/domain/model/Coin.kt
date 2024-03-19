import kotlinx.serialization.Serializable

@Serializable
data class CoinListResponse(
    val data: List<Coin>,
    val timestamp: Long?,
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
) {
    val formattedPrice: String?
        get() {
            priceUsd ?: return null
            val dot = priceUsd.indexOf(".")
            return priceUsd.substring(0, dot + 3)
        }

    val formattedPercent: String?
        get() {
            changePercent24Hr ?: return null
            val dot = changePercent24Hr.indexOf(".")
            return changePercent24Hr.substring(0, dot + 3)
        }

    val isPercent24HrUp: Boolean
        get() = (changePercent24Hr?.toFloat() ?: 0F) > 0F
}

