package com.example.sampledb.data.mapper

import com.example.sampledb.db.UserEntity
import com.example.sampledb.data.remote.dto.UserDto
import com.example.sampledb.domain.model.User

fun UserDto.toEntity() = UserEntity(id, name, email)
fun UserEntity.toDomain() = User(id, name, email)