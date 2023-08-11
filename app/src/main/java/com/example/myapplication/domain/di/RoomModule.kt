package com.example.notes.domain.di

import android.app.Application
import androidx.room.Room
import com.example.myapplication.utils.Constants.Companion.USERS_DATABASE
import com.example.notes.data.local.dao.UsersDao
import com.example.notes.data.local.database.UsersDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Singleton
    fun provideRoomDB( app: Application): UsersDB {
        return Room.databaseBuilder(app, UsersDB::class.java, USERS_DATABASE)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideNotesDao(db: UsersDB): UsersDao {
        return db.usersDao()
    }
}