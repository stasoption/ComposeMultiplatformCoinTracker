package domain.model

import data.sources.dto.Links
import data.sources.dto.LinksExtended
import data.sources.dto.Tag
import data.sources.dto.TeamMember
import data.sources.dto.Whitepaper

data class CoinDetail(
    val id: String,
    val name: String,
    val description: String,
    val symbol: String,
    val developmentStatus: String? = null,
    val firstDataAt: String? = null,
    val hardwareWallet: Boolean,
    val hashAlgorithm: String? = null,
    val isActive: Boolean,
    val isNew: Boolean,
    val lastDataAt: String? = null,
    val links: Links? = null,
    val linksExtended: List<LinksExtended>,
    val message: String? = null,
    val openSource: Boolean,
    val orgStructure: String? = null,
    val proofType: String? = null,
    val rank: Int? = null,
    val startedAt: String? = null,
    val tags: List<Tag>,
    val team: List<TeamMember>,
    val type: String? = null,
    val whitepaper: Whitepaper? = null
)