package com.example.sampledb

import com.example.sampledb.data.remote.dto.UserDto
import com.example.sampledb.data.repository.UserRepositoryImpl
import com.example.sampledb.db.AppDatabase
import com.example.sampledb.domain.repository.UserRepository
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class UserRepositoryTest {
    private lateinit var repository: UserRepository
    private lateinit var fakeRemote: FakeUserRemoteDataSource
    private lateinit var db: AppDatabase

//    @BeforeTest
//    fun setup() {
//        val driver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
//        AppDatabase.Schema.create(driver)
//        db = AppDatabase(driver)
//        fakeRemote = FakeUserRemoteDataSource()
//        repository = UserRepositoryImpl(db, fakeRemote)
//    }
//
//    @Test
//    fun `test refresh updates local database`() = runTest {
//        val mockDto = UserDto(1, "John Doe", "john@example.com")
//        fakeRemote.users = listOf(mockDto)
//
//        repository.refreshUsers()
//
//        val users = repository.getUsers().first()
//        assertEquals(1, users.size)
//        assertEquals("John Doe", users[0].name)
//    }
}