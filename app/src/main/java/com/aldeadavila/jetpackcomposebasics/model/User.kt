package com.aldeadavila.jetpackcomposebasics.model

import androidx.lifecycle.LiveData
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query

@Entity(tableName = "user")
data class User (
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "lastName") val lastName: String,
    @ColumnInfo(name = "city") val city: String,
    @ColumnInfo(name = "state") val state: String,
    @ColumnInfo(name = "thumbnail") val thumbnail: String,
    @ColumnInfo(name = "large") val large: String,
    @ColumnInfo(name = "medium") val medium: String,
    @PrimaryKey(autoGenerate = true) var id: Int = 0
)

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Query("SELECT * FROM user ORDER BY id DESC")
    fun getAll(): LiveData<List<User>>

    @Delete
    fun delete(user: User)
}