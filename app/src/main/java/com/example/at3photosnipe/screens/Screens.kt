package com.example.at3photosnipe.screens

sealed class Screens(val route: String) {
    object JoinGame: Screens("join_game_screen")
    object NewGame: Screens("new_game_screen")
    object SelectGame: Screens("select_game_screen")
    object CreateGame: Screens("create_game_screen")
    object Main: Screens("main_screen")
    object Confirm: Screens("confirm_screen")
    object Snipe: Screens("snipe_screen")
    object Map: Screens("map_screen")
}

//fun nextRoute(cur_route: String?): String{
//    return if(cur_route == null)  "join_game_screen"
//    else {
//        when (cur_route) {
//            "home_screen" -> "card_screen"
//            "card_screen" -> "dice_screen"
//            "dice_screen" -> "slots_screen"
//            else -> ""
//        }
//    }
//}

fun canGoBack(cur_route: String?) : Boolean{
    if(cur_route != null){
        if(cur_route == "join_game_screen") return false
    }
    return true
}