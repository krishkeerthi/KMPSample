package com.example.sampledb.data.remote

import com.example.sampledb.data.remote.dto.RandomUserResponseDto
import com.example.sampledb.data.remote.dto.UserDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

open class UserRemoteDataSource(private val httpClient: HttpClient) {
    open suspend fun fetchUsers(): List<UserDto> {
        val response: RandomUserResponseDto
        =  httpClient.get("https://randomuser.me/api/").body()

        return listOf(UserDto(1L, response.results.first().name?.first ?: "Test Name",
            response.results.first().email ?: "Test email"))
    }
}