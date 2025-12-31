package com.example.sampledb

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(viewmodel: AppViewmodel) {
    val uiState by produceState<UiState>(initialValue = UiState.Loading, key1 = viewmodel) {
        value = UiState.Loading

        try {
            viewmodel.getUsers()
                .catch { e -> value = UiState.Error(e.message ?: "Unknown error") }
                .collectLatest { users ->
                    value = UiState.Success(users.map { it.name })
                }
        } catch (e: Throwable) {
            value = UiState.Error(e.message ?: "Unknown error")
        }
    }

    when (val s = uiState) {
        UiState.Loading -> Text("Loading…", modifier = Modifier.padding(16.dp))
        is UiState.Error -> Text("Error: ${s.message}", modifier = Modifier.padding(16.dp))
        is UiState.Success -> Names(names = s.names)
    }
}

@Composable
private fun Names(names: List<String>) {
    Column(modifier = Modifier
        .background(MaterialTheme.colorScheme.primaryContainer)
        .safeContentPadding()
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,)
    {
        Text("Users", style = MaterialTheme.typography.titleLarge)
        names.forEach { name ->
            Text(text = name, modifier = Modifier.padding(top = 8.dp))
        }
    }
}

sealed interface UiState {
    data object Loading : UiState
    data class Success(val names: List<String>) : UiState
    data class Error(val message: String) : UiState
}
