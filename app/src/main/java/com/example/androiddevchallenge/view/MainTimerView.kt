package com.example.androiddevchallenge.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.MainViewModel
import com.example.androiddevchallenge.ui.theme.MyTheme

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}

// Start building your app here!
@Composable
fun MyApp(mainViewModel: MainViewModel = viewModel()) {
    val time by mainViewModel.time.observeAsState(0)
    val isButtonEnabled by mainViewModel.isStartEnabled.observeAsState(true)

    Surface(color = MaterialTheme.colors.background) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            TimerComponent(time = time, onAdd = { mainViewModel.addTime() }, onDecrease = { mainViewModel.decrementTime() })
            Text(
                text = "Ready... Set...",
                fontSize = 20.sp
            )
            Button(
                enabled = isButtonEnabled,
                onClick = {
                    mainViewModel.startTimer()
                }
            ) {
                Text(text = "EEl!")
            }
        }

    }
}

@Composable
fun TimerComponent(time: Long, onAdd: (Unit) -> Unit, onDecrease: (Unit) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "-",
            fontSize = 52.sp,
            modifier = Modifier
                .clickable(onClick = { onDecrease.invoke(Unit) })
                .padding(16.dp)
        )
        Text(
            text = "$time",
            fontSize = 52.sp
        )
        Text(
            text = "+",
            fontSize = 52.sp,
            modifier = Modifier
                .clickable(onClick = { onAdd.invoke(Unit) })
                .padding(16.dp)
        )
    }
}