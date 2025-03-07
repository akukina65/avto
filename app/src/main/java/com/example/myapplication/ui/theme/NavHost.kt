package com.example.myapplication.ui.theme

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.models.authenticateUser


class AuthViewModel : ViewModel() {
    var isLoggedIn by mutableStateOf(false)
}
@Composable
fun Navigation(authViewModel: AuthViewModel = viewModel()) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") { SplashScreen(navController) }
        composable("screen_1") {
            HorizontalGradientBackgroundExample()
            MyButtons(navController = navController, authViewModel = authViewModel, authenticateUser = ::authenticateUser)
            MyButtons1(navController)
        }
        composable("registration") {
            Regestra(navController, authViewModel) // Передаем AuthViewModel
        }
        composable("screen_2") {
            if (authViewModel.isLoggedIn) {
                Butt2() // Если пользователь авторизован, показать этот экран
            } else {
                Text("Ошибка: Не авторизован")
            }
        }
    }
}

