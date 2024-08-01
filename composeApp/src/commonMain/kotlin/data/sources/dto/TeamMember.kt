package data.sources.dto

import kotlinx.serialization.Serializable

@Serializable
data class TeamMember(
    val id: String? = null,
    val name: String? = null,
    val position: String? = null
)