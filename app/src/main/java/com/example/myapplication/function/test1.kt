package com.example.myapplication.function

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun MyButtons1() {
    //Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Логин",
            modifier = Modifier.padding(start = 25.dp, top = 175.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            color = Color.Black
        )
        Text(
            text = "Пароль",
            modifier = Modifier.padding(start = 25.dp, top = 275.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            color = Color.Black
        )



        // Используем Box для центровки ссылок

    //}
}








@Composable
fun HorizontalGradientBackgroundExample() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(Color(0xFFADD8E6), Color.White) // Teal and White
                )
            )
    )
}




@Preview(showBackground = true)
@Composable
fun HorizontalGradientBackgroundPreview() {
    HorizontalGradientBackgroundExample()
}



