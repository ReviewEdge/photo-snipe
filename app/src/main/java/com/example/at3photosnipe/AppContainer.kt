package com.example.at3photosnipe

import android.content.Context
import androidx.room.Room


interface AppContainer {
    val snipeDao: SnipeDao
    val playerDao: PlayerDao
    val gameInstanceDao: GameInstanceDao
}

class DefaultContainer(val context: Context): AppContainer{

    private val db by lazy {
        Room.databaseBuilder(
            context,
            MyAppDatabase::class.java,
            "mydatabase.db" //giving it a name here
        ).fallbackToDestructiveMigration()   // need to use this, or else you have to describe a migration schema (annoying)
            .build()
    }

    override val snipeDao: SnipeDao by lazy{
        db.snipeDao
    }

    override val playerDao: PlayerDao by lazy{
        db.playerDao
    }

    override val gameInstanceDao: GameInstanceDao by lazy{
        db.gameInstanceDao
    }

}
