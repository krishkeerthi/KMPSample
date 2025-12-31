package com.example.sampledb

import app.cash.sqldelight.db.SqlDriver

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect class DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}

expect fun platformModule(): org.koin.core.module.Module