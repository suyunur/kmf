package com.example.kmf.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User?)

    @Query("SELECT * FROM `user` WHERE username = :username")
    fun getUser(username: String): List<User>

}