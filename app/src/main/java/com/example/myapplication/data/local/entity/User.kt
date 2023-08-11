package com.example.notes.data.local.entity

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.myapplication.utils.Constants.Companion.USERS_TABLE
import com.example.notes.data.enums.UserGender
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
@Entity(tableName = USERS_TABLE, indices = [Index(value = ["userID"], unique = true)])
data class User(
    val userName: String,
    val age: Int,
    val job: String,
    val gender: UserGender
) : Parcelable{
    @PrimaryKey(autoGenerate = true)
    var userID: Int = 0
}
