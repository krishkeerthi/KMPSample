package com.example.sampledb

import androidx.lifecycle.ViewModel
import com.example.sampledb.domain.usecase.GetUsersUseCase
import org.koin.java.KoinJavaComponent.inject

class AppViewmodel: ViewModel() {
    val getUsersUseCase: GetUsersUseCase by inject(GetUsersUseCase::class.java)

    suspend fun getUsers() = getUsersUseCase()
}