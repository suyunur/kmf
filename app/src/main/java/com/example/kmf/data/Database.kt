package com.example.kmf.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [User::class], version = 3, exportSchema = false)

@TypeConverters(Converters::class)
abstract class Database : RoomDatabase() {

    abstract fun userDao() : UserDAO

}