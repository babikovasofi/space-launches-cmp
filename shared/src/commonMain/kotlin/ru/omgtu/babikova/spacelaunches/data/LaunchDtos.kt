package ru.omgtu.babikova.spacelaunches.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.omgtu.babikova.spacelaunches.domain.Agency
import ru.omgtu.babikova.spacelaunches.domain.Launch
import ru.omgtu.babikova.spacelaunches.domain.LaunchPad
import ru.omgtu.babikova.spacelaunches.domain.LaunchProvider
import ru.omgtu.babikova.spacelaunches.domain.LaunchStatus
import ru.omgtu.babikova.spacelaunches.domain.Mission
import ru.omgtu.babikova.spacelaunches.domain.Orbit
import ru.omgtu.babikova.spacelaunches.domain.Pad
import ru.omgtu.babikova.spacelaunches.domain.PadLocation
import ru.omgtu.babikova.spacelaunches.domain.RocketConfiguration

@Serializable
internal data class LaunchPageDto(val next: String?, val results: List<LaunchDto>)

@Serializable
internal data class LaunchDto(
    val id: String,
    val name: String,
    val net: String,
    val status: StatusDto,
    @SerialName("launch_service_provider") val provider: AgencyDto,
    val rocket: RocketDto,
    val mission: MissionDto?,
    val pad: PadDto,
    val image: String?,
) {
    fun toDomain() = Launch(
        id, name, status.toDomain(), net, provider.toProvider(), rocket.configuration.toDomain(),
        mission?.toDomain(), pad.toLaunchPad(), image,
    )
}

@Serializable
internal data class StatusDto(val id: Int, val name: String, val abbrev: String, val description: String) {
    fun toDomain() = LaunchStatus(id, name, abbrev, description)
}

@Serializable
internal data class AgencyDto(
    val id: Int,
    val name: String,
    val type: String?,
    @SerialName("country_code") val countryCode: String? = null,
    @SerialName("founding_year") val foundingYear: String? = null,
    @SerialName("total_launch_count") val totalLaunchCount: Int = 0,
) {
    fun toProvider() = LaunchProvider(id, name, type.orEmpty())

    fun toDomain() = Agency(
        id, name, type.orEmpty(), countryCode.orEmpty(), foundingYear?.toIntOrNull(), totalLaunchCount,
    )
}

@Serializable
internal data class RocketDto(val configuration: RocketConfigurationDto)

@Serializable
internal data class RocketConfigurationDto(
    val id: Int,
    val name: String,
    @SerialName("full_name") val fullName: String,
    val variant: String?,
) {
    fun toDomain() = RocketConfiguration(id, name, fullName, variant.orEmpty())
}

@Serializable
internal data class MissionDto(
    val id: Int,
    val name: String,
    val description: String?,
    val type: String?,
    val orbit: OrbitDto?,
) {
    fun toDomain() = Mission(id, name, description.orEmpty(), type.orEmpty(), orbit?.toDomain())
}

@Serializable
internal data class OrbitDto(val name: String, val abbrev: String) {
    fun toDomain() = Orbit(name, abbrev)
}

@Serializable
internal data class PadDto(
    val id: Int,
    val name: String,
    val latitude: String?,
    val longitude: String?,
    val location: LocationDto,
    @SerialName("country_code") val countryCode: String? = null,
    @SerialName("total_launch_count") val totalLaunchCount: Int = 0,
) {
    fun toLaunchPad() = LaunchPad(id, name, latitude.orEmpty(), longitude.orEmpty(), location.toDomain())

    fun toDomain() = Pad(
        id, name, latitude.orEmpty(), longitude.orEmpty(), location.toDomain(),
        countryCode ?: location.countryCode.orEmpty(), totalLaunchCount,
    )
}

@Serializable
internal data class LocationDto(
    val id: Int,
    val name: String,
    @SerialName("country_code") val countryCode: String?,
) {
    fun toDomain() = PadLocation(id, name, countryCode.orEmpty())
}
