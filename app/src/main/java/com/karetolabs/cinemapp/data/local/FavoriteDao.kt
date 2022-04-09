package com.karetolabs.cinemapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favorite: Favorite)

    @Query("SELECT * FROM favorite_table")
    suspend fun getAllFavorites(): List<Favorite>?

    @Query("SELECT * FROM favorite_table WHERE id = :id LIMIT 1")
    suspend fun getFavoriteById(id: Long): Favorite?


    @Query("DELETE FROM favorite_table")
    suspend fun deleteAllFavorites()

    @Query("DELETE FROM favorite_table WHERE id = :id")
    suspend fun deleteFavoriteId(id: Long)


}