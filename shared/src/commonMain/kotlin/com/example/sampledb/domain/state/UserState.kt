package com.example.sampledb.domain.state

import com.example.sampledb.domain.model.User

data class UserState(
    val isLoading: Boolean = false,
    val users: List<User> = emptyList(),
    val errorMessage: String? = null
)
