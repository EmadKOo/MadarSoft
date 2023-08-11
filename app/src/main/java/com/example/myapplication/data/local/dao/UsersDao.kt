package com.example.notes.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.notes.data.local.entity.User

@Dao
interface UsersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: User): Long

    @Query("select * from users_tbl")
    suspend fun getUsers(): List<User>

    @Query("DELETE from users_tbl")
    suspend fun clearDB()

}