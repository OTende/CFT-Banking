package com.example.cftbin.model.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.cftbin.model.User

@Dao
interface UserDAO {
    @Query("SELECT * FROM User ORDER BY phone ASC")
    suspend fun getAll(): List<User>

    @Insert(onConflict = REPLACE)
    suspend fun insertUser(user: User)
}