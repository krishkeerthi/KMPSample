package com.example.sampledb.data.remote

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import com.example.sampledb.data.remote.dto.UserDto

open class UserRemoteDataSource(private val httpClient: HttpClient) {
    open suspend fun fetchUsers(): List<UserDto> {
        return listOf(UserDto(1L, "keerthi", "keerthi.kc"), UserDto(2L, "sandhya", "sandhya.sd"))//httpClient.get("https://api.example.com/users").body()
    }
}