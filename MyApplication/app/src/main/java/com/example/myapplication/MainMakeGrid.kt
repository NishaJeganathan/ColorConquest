package com.example.myapplication

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

class MainMakeGrid {
    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    
    fun MakeGrid(
        n: Int,
        viewModel: MainViewModel = viewModel(),
        name1: String,
        name2: String,
        onClick4: () -> Unit,
        onclick5: () -> Unit = {}
    ) {
        val background1 = viewModel.background1
        val background2 = viewModel.background2
        val buttonColor = viewModel.buttonColor
        var buttonStatecolor = viewModel.buttonStateColor
        var resultBG by viewModel::resultBG
        var buttonState = viewModel.buttonState
        var numberClicked by viewModel::numberClicked
        var winners by viewModel::winners
        var winnerScore by viewModel::winnerScore
        val playerWins = remember { mutableStateOf(0) }
        var showDialog by remember { mutableStateOf(false) }
        Column (modifier = Modifier.background(MainViewModel().background1)
            .fillMaxSize()){

        }

        val state = remember {
            MutableTransitionState(false).apply {
                // Start the animation immediately.
                targetState = true
            }
        }
        AnimatedVisibility(visibleState = state) {
            Box(modifier = Modifier.background(resultBG)) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp), verticalAlignment = Alignment.Top
                    ) {
                        Button(
                            onClick = onClick4,
                            modifier = Modifier.background(color = buttonColor, shape = CircleShape)
                        ) {
                            Text(
                                text = "x",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = resultBG
                            )
                        }
                        Spacer(modifier = Modifier.width(170.dp))
                        Button(
                            onClick = {
                                buttonState.forEach { it.value = 0 }
                                buttonStatecolor.forEach { it.value = buttonColor }
                                resultBG = background1
                                numberClicked = 0
                                viewModel.Score1 = 0
                                viewModel.Score2 = 0
                            },
                            colors = ButtonDefaults.buttonColors(buttonColor),
                            modifier = Modifier.background(
                                color = buttonColor,
                                shape = RoundedCornerShape(50.dp)
                            ),
                        ) {
                            Text(
                                "Reset",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = resultBG
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(90.dp))
                    AddText1(name1 = name1, viewModel.Score1)
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(n),
                        content =
                        {
                            fun countBlue() {
                                var dummy = 0
                                for (i in 1..n * n - 1) {

                                    if (buttonStatecolor[i].value == background1) {
                                        dummy = dummy + buttonState[i].value
                                        viewModel.Score1 = dummy
                                    }
                                }
                            }

                            fun countRed() {
                                var dummy = 0
                                for (i in 1..n * n - 1) {
                                    if (buttonStatecolor[i].value == background2) {
                                        dummy = dummy + buttonState[i].value
                                        viewModel.Score2 = dummy
                                    }
                                }
                            }


                            fun bombard(i: Int) {
                                if (buttonState[i].value < 3) {
                                    buttonState[i].value++
                                } else {
                                    buttonState[i].value = 0
                                    if (i + n <= n * n) {
                                        if (buttonState[i + n].value < 3) {
                                            buttonState[i + n].value++
                                            buttonStatecolor[i + n].value = resultBG
                                        } else {
                                            bombard(i + n)
                                        }
                                    }
                                    if (i - n >= 0) {
                                        if (buttonState[i - n].value < 3) {
                                            buttonState[i - n].value++
                                            buttonStatecolor[i - n].value = resultBG
                                        } else {
                                            bombard(i - n)
                                        }
                                    }
                                    if ((i % n) != 0) {
                                        if (buttonState[i - 1].value < 3) {
                                            buttonState[i - 1].value++
                                            buttonStatecolor[i - 1].value = resultBG
                                        } else if (buttonState[i - 1].value >= 3) {
                                            bombard(i - 1)
                                        }
                                    }
                                    if ((i + 1) % n != 0) {
                                        if (buttonState[i + 1].value < 3) {
                                            buttonState[i + 1].value++
                                            buttonStatecolor[i + 1].value = resultBG
                                        } else {
                                            bombard(i + 1)
                                        }
                                    }
                                }//else part
                            }

                            items(n * n) { i ->
                                clickButton(
                                    i = i,
                                    onClick = {
                                        if (numberClicked < 2) {
                                            buttonState[i].value = 2
                                        }
                                        bombard(i)
                                        if (resultBG == background1) viewModel.Score1 =
                                            viewModel.Score1 + buttonState[i].value
                                        buttonStatecolor[i].value = resultBG
                                        countBlue()
                                        countRed()
                                        resultBG =
                                            if (resultBG == background1) background2 else background1
                                        numberClicked++
                                        if (numberClicked > 1) {
                                            if (viewModel.Score1 == 0) {
                                                winners = name2
                                                winnerScore = viewModel.Score2
                                                if (name2 in viewModel.players) {
                                                    var integer = viewModel.players.indexOf(name2)
                                                    viewModel.playerSWin[integer]++
                                                } else {
                                                    viewModel.players.add(name2)
                                                    viewModel.playerSWin.add(1)
                                                }
                                                if (!(name1 in viewModel.players)) {
                                                    viewModel.players.add(name1)
                                                    viewModel.playerSWin.add(0)
                                                }
                                            }
                                            if (viewModel.Score2 == 0) {
                                                winners = name1
                                                winnerScore = viewModel.Score1
                                                if (name1 in viewModel.players) {
                                                    var integer = viewModel.players.indexOf(name1)
                                                    viewModel.playerSWin[integer]++
                                                } else {
                                                    viewModel.players.add(name1)
                                                    viewModel.playerSWin.add(1)
                                                }
                                                if (!(name2 in viewModel.players)) {
                                                    viewModel.players.add(name2)
                                                    viewModel.playerSWin.add(0)
                                                }
                                            }
                                        }
                                    },
                                    modifier = Modifier
                                        .aspectRatio(1f)
                                        .padding(5.dp),
                                    shade = if (buttonState[i].value != 0) buttonStatecolor[i].value else buttonColor,
                                    Value = buttonState[i].value.toString(),
                                    enable = if (true) {
                                        when (numberClicked) {
                                            0 -> true
                                            1 -> if (buttonStatecolor[i].value != background1) {
                                                true
                                            } else {
                                                false
                                            }

                                            else -> {
                                                if (resultBG == background1) {
                                                    if (buttonState[i].value == 0) {
                                                        false
                                                    } else {
                                                        if (buttonStatecolor[i].value == buttonColor) {
                                                            false
                                                        } else {
                                                            buttonStatecolor[i].value == background1
                                                        }
                                                    }
                                                } else {
                                                    if (buttonState[i].value == 0) {
                                                        false
                                                    } else {
                                                        if (buttonStatecolor[i].value == buttonColor) {
                                                            false
                                                        } else {
                                                            buttonStatecolor[i].value == background2
                                                        }
                                                    }
                                                }
                                            }

                                        }
                                    } else false
                                )
                            }
                        }//lazy grid scope
                    )//for vertical grid
                    AddText2(name1 = name2, viewModel.Score2)
                    fun abled(): Boolean {
                        if (numberClicked > 1 && (viewModel.Score1 == 0 || viewModel.Score2 == 0))
                            return true
                        else return false
                    }
                    if (numberClicked > 1 && abled()) showDialog = true


                }
            }
        }


        if (showDialog) {
            AlertDialog(onDismissRequest = { showDialog = false },
                title = { Text(text = "GAME OVER", color = Color.Red, fontSize = 20.sp, fontWeight = FontWeight.ExtraBold) },
                text = { Text("$winners won \n score: ${winnerScore.toString()}", fontWeight =
                    FontWeight.Bold) },
                confirmButton = {
                    showDialog = false
                    Button(onClick = {
                        showDialog = false
                        buttonState.forEach { it.value = 0 }
                        buttonStatecolor.forEach { it.value = buttonColor }
                        resultBG = background1
                        numberClicked = 0
                        viewModel.Score1 = 0
                        viewModel.Score2 = 0
                    }, colors = ButtonDefaults.buttonColors(background1)) {
                        Text(text = "Play again")
                    }

                },
                dismissButton = {
                    Button(onClick = onclick5,
                        colors = ButtonDefaults.buttonColors(background2)) {
                        Text(text = "HOME")
                    }
                })
        }


    }

    @Composable
    fun clickButton(
        onClick: () -> Unit,
        modifier: Modifier,
        shade: Color,
        Value: String,
        enable: Boolean,
        i: Int
    ) {
        val a = MainViewModel()
        val buttonColor = Color(0xFFF5E5CE)
        Button(
            onClick = onClick,//{ },
            modifier = modifier,/*,*/
            enabled = enable,
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(buttonColor),
            contentPadding = PaddingValues(0.dp)
        ) {
            if (!enable) {
                a.enabledOrNot[i].value = false
            }
            Box(
                modifier = Modifier
                    .fillMaxSize(0.8f)
                    .clip(CircleShape)
                    .background(shade)
                    .padding(1.dp),
                contentAlignment = Alignment.Center
            )
            {
                Text(
                    "$Value",
                    fontWeight = FontWeight.ExtraBold,
                    color = buttonColor
                )
            }
        }//
    }
}//needed
    @Composable
    fun AddText1(name1:String,s:Int){
        val Score:MainViewModel= viewModel()
        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)){
                Box(
                    modifier = Modifier
                        .background(
                            Color.DarkGray,
                            AbsoluteRoundedCornerShape(topRight = 50.dp, bottomRight = 50.dp)
                        )
                        .padding(vertical = 5.dp, horizontal = 20.dp)
                ) {
                    Text(
                        "$s", fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = MainViewModel().background1,
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.width(15.dp))
                Box (modifier = Modifier
                    .background(
                        Color.DarkGray, AbsoluteCutCornerShape(
                            topLeft = 35.dp,
                            bottomLeft = 35.dp
                        )
                    )
                    .padding(start = 7.dp, end = 7.dp, top = 5.dp, bottom = 5.dp)
                ){
                    Text(
                        "$name1", fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = MainViewModel().background1,
                        textAlign = TextAlign.Center,modifier = Modifier
                            .background(
                                Color.Black, AbsoluteCutCornerShape(
                                    topLeft = 35.dp,
                                    bottomLeft = 35.dp
                                )
                            )
                            .fillMaxWidth(0.60f)
                    )
                }
            }
        }
    @Composable
    fun AddText2(name1:String,s:Int){
        val Score:MainViewModel= viewModel()

        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp)){
            Text("                         ")
            Box(
                modifier = Modifier
                    .background(
                        Color.DarkGray, AbsoluteCutCornerShape(
                            topRight = 35.dp,
                            bottomRight = 35.dp
                        )
                    )
                    .padding(start = 7.dp, end = 7.dp, top = 5.dp, bottom = 5.dp)
                    )
            {
                Text(
                    "$name1", fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color =MainViewModel().background2,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .background(
                            Color.Black, AbsoluteCutCornerShape(
                                topRight = 35.dp,
                                bottomRight = 35.dp
                            )
                        )
                        .fillMaxWidth(0.65f)
                )
            }
            Spacer(modifier = Modifier.width(15.dp))
            Box(
                modifier = Modifier
                    .background(
                        Color.DarkGray,
                        AbsoluteRoundedCornerShape(topLeft = 50.dp, bottomLeft = 50.dp)
                    )
                    .padding(vertical = 5.dp, horizontal = 20.dp)
            ) {
                Text(
                    "$s", fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = MainViewModel().background2,
                    textAlign = TextAlign.Center
                )
            }
        }
    }

@Preview
@Composable
fun PreviewStuff(){
    MainMakeGrid().MakeGrid(n = 5, name1 = "Nisha", name2 = "Prithika", onClick4 = {})
}
