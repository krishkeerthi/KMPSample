package com.example.sampledb

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampledb.domain.state.UserState
import com.example.sampledb.domain.usecase.GetUsersUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import org.koin.java.KoinJavaComponent.inject

class AppViewmodel: ViewModel() {
    val getUsersUseCase: GetUsersUseCase by inject(GetUsersUseCase::class.java)

    val uiState: StateFlow<UserState> = getUsersUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = UserState(isLoading = true)
        )
}