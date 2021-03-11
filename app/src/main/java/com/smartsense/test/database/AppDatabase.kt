package com.smartsense.test.database


import androidx.room.Database
import androidx.room.RoomDatabase
import com.smartsense.test.database.dao.UserDao
import com.smartsense.test.database.entity.User
import com.smartsense.test.database.entity.UserDB


@Database(entities = [UserDB::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val userDao: UserDao
}