package com.example.sampledb.domain.usecase

import com.example.sampledb.domain.repository.UserRepository
import com.example.sampledb.domain.state.UserState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

class GetUsersUseCase(private val repository: UserRepository) {
    @Throws(Exception::class)
    operator fun invoke(): Flow<UserState> =
        repository.getUsers().map { users ->
            UserState(users = users, isLoading = false)
        }
            .onStart {
                emit(UserState(isLoading = true))
                try {
//                    repository.refreshIfEmpty()
                    repository.refreshUsers()
                } catch (e: Exception) {
                    emit(UserState(isLoading = false, errorMessage = "Network failed: ${e.message}"))
                }
            }
            .catch { e ->
                emit(UserState(errorMessage = e.message, isLoading = false))
            }
}