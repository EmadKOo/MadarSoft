package com.example.myapplication.data.repository

import com.example.notes.data.local.dao.UsersDao
import com.example.notes.data.local.entity.User
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private var usersDao: UsersDao) : UserRepository {
    override suspend fun addUser(user: User): Long = usersDao.addUser(user)

    override suspend fun getUsers(): List<User> = usersDao.getUsers()

    override suspend fun clearUsers() = usersDao.clearDB()

}