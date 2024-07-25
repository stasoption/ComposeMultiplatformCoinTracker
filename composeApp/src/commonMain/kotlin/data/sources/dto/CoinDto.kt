package data.sources.dto

import domain.model.Coin
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinDto(
    val id: String,
    @SerialName("is_active")
    val isActive: Boolean,
    @SerialName("is_new")
    val isNew: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String
)

fun CoinDto.toCoin(): Coin {
    return Coin(
        id = this.id,
        isActive = this.isActive,
        isNew = this.isNew,
        name = this.name,
        rank = this.rank,
        symbol = this.symbol,
        type = this.type
    )
}