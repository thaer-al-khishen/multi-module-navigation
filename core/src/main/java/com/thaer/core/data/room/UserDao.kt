package com.thaer.core.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thaer.core.data.models.local.User

@Dao
interface UserDao {
    @Insert
    suspend fun addUser(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<User>)

    @Query("SELECT * FROM user_table")
    suspend fun getAllUsers(): List<User>

    // more functions can be added here for updating, deleting etc.
}
