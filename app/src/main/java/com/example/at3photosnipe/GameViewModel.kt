package com.example.at3photosnipe

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.at3photosnipe.data.GameInstance
import com.example.at3photosnipe.data.Snipe
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.random.Random


@Entity(tableName = "player")
data class Player(
    @PrimaryKey(autoGenerate = true)
    val player_id: Int,
    val name: String,
    var score: Int,
    val gameInstanceId: Int
)


//class GameViewModel : ViewModel() {
class GameViewModel(
    private val snipeDao: SnipeDao,
    private val playerDao: PlayerDao,
    private val gameInstanceDoa: GameInstanceDao
) : ViewModel() {

    // get snipes from db
    val snipes = snipeDao.getAllSnipes()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList()) //this function will be called IF there is a subscriber to this flow

    // get players from db
    val allPlayersList = playerDao.getAllPlayers()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList()) //this function will be called IF there is a subscriber to this flow

    // get games from db
    val gameInstances = gameInstanceDoa.getAllGameInstances()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList()) //this function will be called IF there is a subscriber to this flow


    var currentGameInstance: GameInstance? = null
    var currentPlayer: Player? = null




//    init {
//        createPlayerTable()
//        createSnipeTable()
//    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun newSnipe(
        pictureRes: String,
        snipeeId: Int,
        location: String,
    ): Snipe {

        val tOD = MyDateUtils.getCurrentDateTimeString()

        val newSnipe = Snipe(
            snipe_id = generateSnipeId(),
            picture_res = pictureRes,
            sniper_id = currentPlayer!!.player_id,
            snipee_id = snipeeId,
            time_of_day = tOD,
            //TODO: get the location data
            location = location,
            gameInstanceId = currentGameInstance!!.game_id
        )

        // save new snipe to db
        viewModelScope.launch {
            snipeDao.upsertSnipe(newSnipe)
        }

        // add point
        currentPlayer!!.score = currentPlayer!!.score+1
        saveCurrentGameAndPlayerToDB()

        return newSnipe
    }

    fun myFromList(value: List<Int>?): String? {
        return value?.joinToString(",")
    }

    fun myFromListHandlesNull(value: List<Int>?): String {
        return value?.joinToString(",") + ""
    }

    fun myToList(value: String?): List<Int>? {
        return value?.split(",")?.map { it.toInt() }
    }


    @Composable
    fun getPlayerById(findPlayerID : Int): Player? {
//        val playerList by players.collectAsState()
        val playerList: List<Player> by playerDao.getAllPlayers().collectAsState(emptyList())

        return playerList.find { it.player_id == findPlayerID }
    }

    // TESTING
    private fun createPlayerTable() {
        val listOfPlayers = listOf(
            Player(player_id = 0, name = "Player 1a", score = 18, gameInstanceId = 0),
            Player(player_id = 0, name = "Player 2", score = 18, gameInstanceId = 0),
            Player(player_id = 0, name = "Player 3", score = 14, gameInstanceId = 0),
            Player(player_id = 0, name = "Player 4", score = 14, gameInstanceId = 0),
        )


        viewModelScope.launch {
            listOfPlayers.forEach{
                playerDao.upsertPlayer(it)
            }
        }

    }


    // TESTING
//    private fun createSnipeTable() {
//        // fills db with test data
//
//        val s1 = Snipe(picture_res = R.drawable.architecture, sniper_id = 0, snipee_id = 1, time_of_day = "10:27 AM", snipe_id = 0, gameInstanceId = 0)
//        val s2 = Snipe(picture_res = R.drawable.business, sniper_id = 1, snipee_id = 0, time_of_day = "10:52 AM", snipe_id = 0, gameInstanceId = 0)
//        val s3 = Snipe(picture_res = R.drawable.culinary, sniper_id = 2, snipee_id = 0, time_of_day = "11:08 AM", snipe_id = 0, gameInstanceId = 0)
//        val s4 = Snipe(picture_res = R.drawable.film, sniper_id = 3, snipee_id = 2, time_of_day = "11:15 AM", snipe_id = 0, gameInstanceId = 0)
//
//        val listOfSnipes = listOf(s1,s2,s3,s4)
//
//        viewModelScope.launch {
//            listOfSnipes.forEach{
//                snipeDao.upsertSnipe(it)
//            }
//        }
//    }


    fun gameExists(): Boolean {
        return currentGameInstance != null
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


    @RequiresApi(Build.VERSION_CODES.O)
    fun generatePlayerId(): Int {
        val currentDateTime = LocalDateTime.now()
        val timestamp = currentDateTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")).toLong()
        return timestamp.hashCode()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun generateGameId(): Int {
        val currentDateTime = LocalDateTime.now()
        val timestamp = currentDateTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")).toLong()
        return timestamp.hashCode()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun generateSnipeId(): Int {
        val currentDateTime = LocalDateTime.now()
        val timestamp = currentDateTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")).toLong()
        return timestamp.hashCode()
    }

      @RequiresApi(Build.VERSION_CODES.O)
      fun createGame(newGameName: String, firstPlayerName: String) {

          val newGameId = generateGameId()
          val newPlayerId = generatePlayerId()

          val firstPlayer = Player(name = firstPlayerName, score = 0, player_id = newPlayerId, gameInstanceId = newGameId)


          var newGame = myFromList(listOf(newPlayerId))?.let {
            GameInstance(
                game_id = newGameId,
                game_name = newGameName,
                players_in_game = it,
                admin_player_id = newPlayerId,
                gameJoinCode = generateJoinCode()
            )
          }

          currentPlayer = firstPlayer
          currentGameInstance = newGame

          saveCurrentGameAndPlayerToDB()
    }

    fun savePlayerToDB(p: Player) {
        viewModelScope.launch {
            playerDao.upsertPlayer(p)
        }
    }

    fun saveCurrentGameAndPlayerToDB() {
        viewModelScope.launch {
            currentGameInstance?.let { gameInstanceDoa.upsertGameInstance(it) }
        }
        viewModelScope.launch {
            currentPlayer?.let { playerDao.upsertPlayer(it) }
        }
    }

    private fun givePlayerPoint(p: Player) {
        p.score += 1
        viewModelScope.launch {
            playerDao.upsertPlayer(p)
        }
    }



    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("CoroutineCreationDuringComposition")
    fun createPlayerAndAddToGameAndSetCurrents(newPlayerName: String, game: GameInstance) {


        val newPlayerId = generatePlayerId()
        val newP = Player(player_id = newPlayerId, name=newPlayerName, score=0, gameInstanceId = game.game_id)


        // make new player list with new player on it
        val oldPlayersList = myToList(game.players_in_game)
        val newPlayersList = oldPlayersList?.plus(newPlayerId)


        game.players_in_game = myFromListHandlesNull(newPlayersList)

        currentPlayer = newP
        currentGameInstance = game

        // save to DB
        saveCurrentGameAndPlayerToDB()
    }

    // Does not delete player from database (will not be on scoreboard, but will still show up in snipes)
    @SuppressLint("CoroutineCreationDuringComposition")
    fun removePlayer(deleteMe: Player, gameToRemoveFrom: GameInstance? = currentGameInstance) {
        // make new player list without player on it
        val oldPlayersList = myToList(gameToRemoveFrom?.players_in_game)

        var newPlayerListMut: MutableList<Int> = mutableListOf()

        oldPlayersList?.forEach { if(it != deleteMe.player_id) {newPlayerListMut.add(it)} }

        val newPlayersList = newPlayerListMut.toList()

        if (gameToRemoveFrom != null) {
            gameToRemoveFrom.players_in_game = myFromListHandlesNull(newPlayersList)
        }

        // save game to DB
        viewModelScope.launch {
            gameToRemoveFrom?.let { gameInstanceDoa.upsertGameInstance(it) }
        }

    }


    fun getJoinCode(): String? {
        return currentGameInstance?.gameJoinCode
    }
    fun getGameName(): String? {
        return currentGameInstance?.game_name
    }

    @Composable
    fun getPlayersInGame(game: GameInstance? = currentGameInstance): List<Player> {
        val pIdsList = myToList(game?.players_in_game)

        var pList: List<Player> = listOf()

        pIdsList?.forEach {
            val p = getPlayerById(findPlayerID = it)

            println(p)

            if (p != null) {
                pList = (pList + p)

            }
        }


        return pList

    }


    @Composable
    fun isNameTaken(checkName: String): Boolean {
        val playerList: List<Player> by playerDao.getAllPlayers().collectAsState(emptyList())
        return playerList.find { it.name == checkName } != null
    }



    fun isGameManager(): Boolean {
        return currentPlayer?.player_id == currentGameInstance?.admin_player_id
    }


//    fun makeSomeFakeSnipesForTESTING() {
//        val players = listOf(
//            Player(name = "Player 2", score = 18),
//            Player(name = "Player 3", score = 18),
//            Player(name = "Player 4", score = 14),
//            // Add more players as needed
//        )
//
//        val s1 = Snipe(picture_res = R.drawable.architecture, sniper = getCurrentPlayer(), snipee = players[1], "10:27 AM")
//        val s2 = Snipe(picture_res = R.drawable.business, sniper = players[1], snipee = players[0], "10:52 AM")
//        val s3 = Snipe(picture_res = R.drawable.culinary, sniper = players[2], snipee = getCurrentPlayer(), "11:08 AM")
//        val s4 = Snipe(picture_res = R.drawable.film, sniper = getCurrentPlayer(), snipee = players[2], "11:15 AM")
//
//        gameState!!.value.snipes = mutableListOf(s1,s2,s3,s4)
//    }


    companion object {
        private var INSTANCE : GameViewModel? = null

        fun getInstance(): GameViewModel{
            val vm = INSTANCE ?: synchronized(this){
                GameViewModel(
                    snipeDao = PhotoSnipeRoomApp.getApp().container.snipeDao,
                    playerDao = PhotoSnipeRoomApp.getApp().container.playerDao,
                    gameInstanceDoa = PhotoSnipeRoomApp.getApp().container.gameInstanceDao
                ).also {
                    INSTANCE = it
                }
            }
            return vm
        }

    }


}