package com.example.sampledb

import com.example.sampledb.data.remote.UserRemoteDataSource
import com.example.sampledb.data.remote.dto.UserDto
import io.ktor.client.HttpClient

class FakeUserRemoteDataSource : UserRemoteDataSource(HttpClient()) { // Simplified
    var users = listOf<UserDto>()
    override suspend fun fetchUsers(): List<UserDto> = users
}