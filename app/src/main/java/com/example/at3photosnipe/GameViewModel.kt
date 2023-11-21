package com.example.at3photosnipe

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.random.Random

data class Player(val name: String, val score: Int)

data class Snipe(
    val pictureRes: Int,
    val sniper: Player,
    val snipee: Player,
    val timeOfDay: String,
    val location: String? = null,
    )

data class GameState(
    val gameName: String,
    val gameJoinCode: String,
    val players: MutableList<Player> = mutableListOf(),
    var snipes: MutableList<Snipe> = mutableListOf(),
    val gameAdmin: Player,
    var currentPlayer: Player,
)

class GameViewModel : ViewModel() {
    var gameState: StateFlow<GameState>? = null

    init {
    }

    fun gameExists(): Boolean {
        return gameState != null
    }

    private fun generateJoinCode(): String {
        val random = Random(System.currentTimeMillis()) // Seed with current time for better randomness

        // Generate four random digits
        val randomDigits = (1..4).joinToString("") { random.nextInt(0, 10).toString() }

        // Generate three random capital letters
        val randomLetters = (1..3).joinToString("") { ('A' + random.nextInt(26)).toString() }

        // Concatenate the random digits and letters
        return randomDigits + randomLetters
    }
    fun createGame(newGameName: String, firstPlayerName: String) {
        if (gameState == null) {
            val firstPlayer = Player(name = firstPlayerName, score = 0)

            var _gameState = MutableStateFlow<GameState>(
                GameState(
                    gameName = newGameName,
                    gameAdmin = firstPlayer,
                    currentPlayer = firstPlayer,
                    gameJoinCode = generateJoinCode()
                )
            )
            gameState = _gameState.asStateFlow()
            addPlayer(firstPlayer)
        } else {
            throw Exception("There is already an existing game")
        }
    }

    fun addSnipe(newSnipe: Snipe) {
        gameState!!.value.snipes.add(newSnipe)
    }

    fun addPlayer(newPlayer: Player) {
        gameState!!.value.players.add(newPlayer)
    }
    fun removePlayer(deleteMe: Player) {
        gameState!!.value.players.removeIf { it == deleteMe }
    }

//    fun removePlayerByName(deleteMeName: String) {
//        gameState!!.value.players.removeIf { it.name == deleteMeName }
//    }

    fun getJoinCode(): String {
        return gameState!!.value.gameJoinCode
    }
    fun getGameName(): String {
        return gameState!!.value.gameName
    }

    fun getPlayers(): MutableList<Player> {
        return gameState!!.value.players
    }

    fun getCurrentPlayer(): Player {
        return gameState!!.value.currentPlayer
    }

    fun isNameTaken(checkName: String): Boolean {
        return gameState!!.value.players.any { it.name == checkName }
    }

    fun setCurrentPlayer(p: Player) {
        gameState!!.value.currentPlayer = p
    }

    fun isGameMangager(): Boolean {
        return getCurrentPlayer() == gameState!!.value.gameAdmin
    }

    fun makeSomeFakeSnipesForTESTING() {
        val players = listOf(
            Player(name = "Player 2", score = 18),
            Player(name = "Player 3", score = 18),
            Player(name = "Player 4", score = 14),
            // Add more players as needed
        )

        val s1 = Snipe(pictureRes = R.drawable.architecture, sniper = getCurrentPlayer(), snipee = players[1], "10:27 AM")
        val s2 = Snipe(pictureRes = R.drawable.business, sniper = players[1], snipee = players[0], "10:52 AM")
        val s3 = Snipe(pictureRes = R.drawable.culinary, sniper = players[2], snipee = getCurrentPlayer(), "11:08 AM")
        val s4 = Snipe(pictureRes = R.drawable.film, sniper = getCurrentPlayer(), snipee = players[2], "11:15 AM")

        gameState!!.value.snipes = mutableListOf(s1,s2,s3,s4)
    }

    fun getSnipes(): MutableList<Snipe> {
        return gameState!!.value.snipes
    }


}