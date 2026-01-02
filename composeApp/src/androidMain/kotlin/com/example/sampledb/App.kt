package com.example.sampledb

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(viewmodel: AppViewmodel) {

    val uiState by viewmodel.uiState.collectAsStateWithLifecycle()

    Box(modifier = Modifier.fillMaxSize()){
        if(uiState.users.isNotEmpty()){
            Names(names = uiState.users.map { it.name })
        }

        if(uiState.isLoading){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }

        uiState.errorMessage?.let { message ->
            if(uiState.users.isEmpty()){
                Text(
                    text = "Error: $message",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(16.dp)
                )
            }
        }
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
