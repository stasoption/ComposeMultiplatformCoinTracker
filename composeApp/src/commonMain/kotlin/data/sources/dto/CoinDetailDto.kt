package data.sources.dto

import domain.model.CoinDetail
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CoinDetailDto(
    val id: String,
    val name: String,
    val description: String,
    val symbol: String,
    @SerialName("development_status")
    val developmentStatus: String? = null,
    @SerialName("first_data_at")
    val firstDataAt: String? = null,
    @SerialName("hardware_wallet")
    val hardwareWallet: Boolean,
    @SerialName("hash_algorithm")
    val hashAlgorithm: String? = null,
    @SerialName("is_active")
    val isActive: Boolean,
    @SerialName("is_new")
    val isNew: Boolean,
    @SerialName("last_data_at")
    val lastDataAt: String? = null,
    val links: Links? = null,
    @SerialName("links_extended")
    val linksExtended: List<LinksExtended>? = null,
    val message: String? = null,
    @SerialName("open_source")
    val openSource: Boolean,
    @SerialName("org_structure")
    val orgStructure: String? = null,
    @SerialName("proof_type")
    val proofType: String? = null,
    val rank: Int? = null,
    @SerialName("started_at")
    val startedAt: String? = null,
    val tags: List<Tag>? = null,
    val team: List<TeamMember>? = null,
    val type: String? = null,
    val whitepaper: Whitepaper? = null
)

fun CoinDetailDto.toCoinDetail(): CoinDetail {
    return CoinDetail(
        id = this.id,
        name = this.name,
        description = this.description,
        symbol = this.symbol,
        developmentStatus = this.developmentStatus,
        firstDataAt = this.firstDataAt,
        hardwareWallet = this.hardwareWallet,
        hashAlgorithm = this.hashAlgorithm,
        isActive = this.isActive,
        isNew = this.isNew,
        lastDataAt = this.lastDataAt,
        links = this.links,
        linksExtended = this.linksExtended.orEmpty(),
        message = this.message,
        openSource = this.openSource,
        orgStructure = this.orgStructure,
        proofType = this.type,
        rank = this.rank,
        startedAt = this.startedAt,
        tags = this.tags.orEmpty(),
        team = this.team.orEmpty(),
        type = this.type,
        whitepaper = this.whitepaper,
    )
}