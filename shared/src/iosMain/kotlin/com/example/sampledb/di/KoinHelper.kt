package com.example.sampledb.di

import com.example.sampledb.domain.repository.UserRepository
import com.example.sampledb.domain.usecase.GetUsersUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class KoinHelper : KoinComponent {

    private val userRepository: UserRepository by inject()
    private val getUsersUseCase: GetUsersUseCase by inject()

    @Throws(Throwable::class)
    fun getUsersUseCase(): GetUsersUseCase = getUsersUseCase

    @Throws(Throwable::class)
    suspend fun refreshUsers() {
        userRepository.refreshUsers()
    }
}