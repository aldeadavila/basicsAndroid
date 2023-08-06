package com.aldeadavila.jetpackcomposebasics.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aldeadavila.jetpackcomposebasics.model.User
import com.aldeadavila.jetpackcomposebasics.model.UserDao

@Database(entities = [User::class], version = 1)
abstract class DbDataSource : RoomDatabase() {

    abstract fun userDao(): UserDao
}