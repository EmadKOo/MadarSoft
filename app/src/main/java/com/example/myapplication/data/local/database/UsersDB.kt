package com.example.notes.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notes.data.local.dao.UsersDao
import com.example.notes.data.local.entity.User

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
)
abstract class UsersDB : RoomDatabase() {
    abstract fun usersDao(): UsersDao

}