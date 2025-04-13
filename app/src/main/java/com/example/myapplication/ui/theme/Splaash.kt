package com.example.myapplication.ui.theme

import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.supabasesimpleproject.R
import kotlinx.coroutines.delay

import androidx.compose.material3.Text
import android.annotation.SuppressLint


import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember

import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

import androidx.compose.animation.core.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp

import kotlinx.coroutines.delay

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*

import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset


@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember { Animatable(1f) }
    val alpha = remember { Animatable(0f) }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 1.5f,
            animationSpec = tween(
                durationMillis = 2000,
                easing = LinearEasing
            )
        )

        alpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 500,
                easing = LinearEasing
            )
        )

        delay(2000L)

        navController.navigate("screen_1") {
            popUpTo("screen_1") { inclusive = true }
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Твори добро!",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 30.sp,
                modifier = Modifier
                    .graphicsLayer(alpha = alpha.value)
                    .padding(bottom = 80.dp) // Отступ под текстом
            )

            Image(
                painter = painterResource(id = R.drawable.heart),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(200.dp)
                    .scale(scale.value)
            )
        }
    }
}



















