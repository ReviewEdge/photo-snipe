package com.example.at3photosnipe.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "snipe")
data class Snipe(
    @PrimaryKey(autoGenerate = true)
    val snipe_id: Int,
    val picture_res: String,
    val sniper_id: Int,
    val snipee_id: Int,
    val time_of_day: String,
    val location: String = "",
    val gameInstanceId: Int
)
