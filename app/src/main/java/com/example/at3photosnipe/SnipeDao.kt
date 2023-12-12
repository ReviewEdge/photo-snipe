package com.example.at3photosnipe

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface SnipeDao {
    @Upsert
    suspend fun upsertSnipe(snipe: Snipe)

    @Delete
    suspend fun deleteSnipe(snipe: Snipe)

    @Query("SELECT * FROM snipe")
    fun getAllSnipes(): Flow<List<Snipe>>
}
