package data.sources.dto

import kotlinx.serialization.Serializable

@Serializable
data class LinksExtended(
    val stats: Stats? = null,
    val type: String? = null,
    val url: String? = null
)