package com.example.at3photosnipe.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game_instance")
data class GameInstance(
    @PrimaryKey(autoGenerate = true)
    val game_id: Int,
    val game_name: String,
    var players_in_game: String,
    var admin_player_id: Int,
    val gameJoinCode: String,
    var active: Boolean = true
)