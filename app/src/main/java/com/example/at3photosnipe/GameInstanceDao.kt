package com.example.at3photosnipe


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.at3photosnipe.data.GameInstance
import kotlinx.coroutines.flow.Flow

@Dao
interface GameInstanceDao {
    @Upsert
    suspend fun upsertGameInstance(gameInstance: GameInstance)

    @Delete
    suspend fun deleteGameInstance(gameInstance: GameInstance)

    @Query("SELECT * FROM game_instance")
    fun getAllGameInstances(): Flow<List<GameInstance>>


    @Query("SELECT * FROM game_instance")
    fun getAllGameInstancesNotFlow(): List<GameInstance>
}
