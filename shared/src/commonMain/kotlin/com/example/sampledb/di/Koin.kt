package com.example.sampledb.di

import com.example.sampledb.DatabaseDriverFactory
import com.example.sampledb.data.remote.UserRemoteDataSource
import com.example.sampledb.data.repository.UserRepositoryImpl
import com.example.sampledb.db.AppDatabase
import com.example.sampledb.domain.repository.UserRepository
import com.example.sampledb.domain.usecase.GetUsersUseCase
import com.example.sampledb.platformModule
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(commonModule)
    }

// Called from iOS
fun initKoinIos() = initKoin {
    modules(platformModule())
}

val commonModule = module {
    single { HttpClient {
        install(ContentNegotiation) { json(
            Json {
                ignoreUnknownKeys = true
                isLenient = true
            }
        ) }
    } }

    single { UserRemoteDataSource(get()) }
    // Database
    single { AppDatabase(get<DatabaseDriverFactory>().createDriver()) }

    single<UserRepository> { UserRepositoryImpl(get(), get()) }

    single { GetUsersUseCase(get()) }


}