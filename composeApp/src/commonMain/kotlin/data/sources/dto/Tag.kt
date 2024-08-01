package data.sources.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Tag(
    @SerialName("coin_counter")
    val coinCounter: Int? = null,
    @SerialName("ico_counter")
    val icoCounter: Int? = null,
    val id: String? = null,
    val name: String? = null
)