package com.example.sampledb.domain.usecase

import com.example.sampledb.domain.model.User
import com.example.sampledb.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class GetUsersUseCase(private val repository: UserRepository) {
//    suspend operator fun invoke(): Flow<List<User>> = repository.refreshUsers().let{
//        repository.getUsers()
//    }

    @Throws(Exception::class)
    suspend operator fun invoke(): Flow<List<User>> = flowOf(listOf(User(1, "John Doe", ""), User(2, "Jane Smith", "")))
}