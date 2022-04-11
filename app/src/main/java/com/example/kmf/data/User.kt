package com.example.kmf.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "user")
data class User(
    val id: Long?,
    @PrimaryKey val username: String,
    val firstName: String?,
    val lastName: String?,
    val email: String?,
    val password: String?,
    val phone: String?,
    val userStatus: Int?
): Serializable
