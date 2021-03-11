package com.smartsense.test.database.dao


import androidx.room.*
import com.smartsense.test.database.entity.UserDB

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    suspend fun findAllUsers(): List<UserDB>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(recipes: List<UserDB>)

    @Delete
    suspend fun delete(recipeDB:  UserDB)



}