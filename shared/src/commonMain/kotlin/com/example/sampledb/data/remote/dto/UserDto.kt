package com.example.sampledb.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(val id: Long, val name: String, val email: String)

@Serializable
data class RandomUserResponseDto(
    @SerialName("results") val results: List<UsersDto> = emptyList()
)

@Serializable
data class UsersDto(
    @SerialName("email") val email: String? = null,
    @SerialName("name") val name: NameDto? = null,
    @SerialName("picture") val picture: PictureDto? = null
)

@Serializable
data class NameDto(
    @SerialName("first") val first: String? = null,
    @SerialName("last") val last: String? = null
)

@Serializable
data class PictureDto(
    @SerialName("large") val large: String? = null,
    @SerialName("thumbnail") val thumbnail: String? = null
)