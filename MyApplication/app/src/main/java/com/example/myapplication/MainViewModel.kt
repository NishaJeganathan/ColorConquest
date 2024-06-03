package com.example.myapplication
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val background1 = Color(0xFF2FB6F0)
    val background2 = Color(0xFFFF5F57)
    val buttonColor = Color(0xFFF5E5CE)
    var player1 by  mutableStateOf("")
    var player2 by mutableStateOf("")
    var Score1 by mutableStateOf(0)
    var Score2 by mutableStateOf(0)
    var resultBG by mutableStateOf(background1)
    var buttonState = List(49) { mutableStateOf(0) }
    var buttonStateColor = List(49) { mutableStateOf(buttonColor) }
    var numberClicked by mutableStateOf(0)
    var winners by mutableStateOf("")
    var winnerScore by mutableStateOf(0)
    var enabledOrNot =List(49){ mutableStateOf(false) }
    var players = mutableListOf<String>()
    var playerSWin = mutableListOf<Int>()

}