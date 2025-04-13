package com.example.myapplication.function

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.Components.DetailcardScreen
import com.example.myapplication.Components.MainScreen

import com.example.myapplication.SignIn.SignInScreen
import com.example.myapplication.SignUpScreen.SignUpScreen

import com.example.myapplication.ui.theme.SplashScreen


@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            HorizontalGradientBackgroundExample()
            SplashScreen(navController)

        }
        composable("screen_1") {
            HorizontalGradientBackgroundExample()

            SignInScreen(navController)
            MyButtons1()

        }
        composable("screen_3") {
            HorizontalGradientBackgroundExample()
            SignUpScreen(navController)

        }
        composable("screen_2") {

            MainScreen(navController)

        }
        composable(
            route = "detailsbook" + "/{id}",
            arguments = listOf(navArgument("id"){
                type = NavType.StringType
            })
        ){
            val id = it.arguments?.getString("id")
            if(id != null)
            {
                DetailcardScreen(navController, id)
            }
        }


        }





    }


