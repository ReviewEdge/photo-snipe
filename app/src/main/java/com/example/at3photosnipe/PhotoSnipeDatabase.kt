package com.example.at3photosnipe

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.at3photosnipe.data.GameInstance

@Database(
    entities = [Snipe::class, Player::class, GameInstance::class],
    version = 3
)
abstract class MyAppDatabase : RoomDatabase() {
    abstract val snipeDao: SnipeDao
    abstract val playerDao: PlayerDao
    abstract val gameInstanceDao: GameInstanceDao
}