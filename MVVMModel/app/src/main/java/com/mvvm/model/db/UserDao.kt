package com.mvvm.model.db

import androidx.room.*


/**
 * UserDao.kt
 * Info:
 * Created by cxd on 2020-03-31 15:27
 */
@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun  createUser(user: User)

    @Query("SELECT * FROM User")
    fun findAll(): List<User>

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)

}