package com.example.myapplication.data.repository

import com.example.notes.data.local.entity.User

interface UserRepository {
    suspend fun addUser(user: User): Long
    suspend fun getUsers(): List<User>
    suspend fun clearUsers()
}