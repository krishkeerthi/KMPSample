package com.example.sampledb.data.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.example.sampledb.data.mapper.toDomain
import com.example.sampledb.data.mapper.toEntity
import com.example.sampledb.data.remote.UserRemoteDataSource
import com.example.sampledb.db.AppDatabase
import com.example.sampledb.domain.model.User
import com.example.sampledb.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepositoryImpl(
    private val db: AppDatabase,
    private val remoteDataSource: UserRemoteDataSource
) : UserRepository {

    override fun getUsers(): Flow<List<User>> {
        return db.userEntityQueries.selectAllUsers()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { list -> list.map { it.toDomain() } }
    }

    override suspend fun refreshIfEmpty() {
        val currentData = db.userEntityQueries.selectAllUsers().executeAsList()

        if (currentData.isEmpty()) {
            try {
                val remoteUsers = remoteDataSource.fetchUsers()
                db.transaction {
                    remoteUsers.forEach { dto ->
                        db.userEntityQueries.insertUser(dto.id, dto.name, dto.email)
                    }
                }
            } catch (e: Exception) {
                throw e
            }
        }
    }

    override suspend fun refreshUsers() {
        val remoteUsers = remoteDataSource.fetchUsers()
        db.userEntityQueries.transaction {
            db.userEntityQueries.deleteAllUsers()
            remoteUsers.forEach { dto ->
                val entity = dto.toEntity()
                db.userEntityQueries.insertUser(entity.id, entity.name, entity.email)
            }
        }
    }
}