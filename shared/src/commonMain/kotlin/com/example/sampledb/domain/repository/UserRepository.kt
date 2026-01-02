package com.example.sampledb.domain.repository

import com.example.sampledb.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUsers(): Flow<List<User>>

    suspend fun refreshIfEmpty()
    suspend fun refreshUsers()
}