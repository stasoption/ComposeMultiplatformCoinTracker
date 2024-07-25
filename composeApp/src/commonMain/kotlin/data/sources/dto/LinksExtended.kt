package data.sources.dto

import kotlinx.serialization.Serializable

@Serializable
data class LinksExtended(
    val stats: Stats,
    val type: String,
    val url: String
)