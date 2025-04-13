package com.example.myapplication.SignIn

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.SignUp.ResultState
import com.example.myapplication.SignUpScreen.ButtonNavigation
import com.example.myapplication.SignUpScreen.TextFieldEmail
import com.example.myapplication.SignUpScreen.TextFieldPassword
import com.example.supabasesimpleproject.R
import androidx.compose.ui.draw.scale
@Composable
fun SignInScreen(navController: NavController, signInViewModel: SignInViewModel = viewModel())
{


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.child),
            contentDescription = "Logo",

        )
        Spacer(modifier = Modifier.height(15.dp))

    }

    val resultState by signInViewModel.resultState.collectAsState()
    val uiState = signInViewModel.uiState

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        //verticalArrangement = Arrangement.Center
    ){
        Spacer(modifier = Modifier.height(200.dp))
        TextFieldEmail(value = uiState.email, error = uiState.errorEmail,
            onvaluechange = {it -> signInViewModel.updateState(uiState.copy(email = it))})
        Spacer(Modifier.height(30.dp))
        TextFieldPassword(uiState.password) {
            signInViewModel.updateState(uiState.copy(password = it))
        }
        Spacer(Modifier.height(10.dp))

        when (resultState)
        {
            is ResultState.Error -> {
                ButtonNavigation("Войти") {
                    signInViewModel.signIn().toString()

                }
                Text((resultState as ResultState.Error).message)
            }
            is ResultState.Initialized ->
            {
                ButtonNavigation("Войти") {
                    signInViewModel.signIn().toString()
                }
            }
            ResultState.Loading -> {
                CircularProgressIndicator()
            }
            is ResultState.Success -> {
                navController.navigate("screen_2")
                {
                    popUpTo("screen_1")
                    {
                        inclusive = true
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp),
            contentAlignment = Alignment.Center // Центрируем содержимое
        ) {
            Text(
                text = "Создать аккаунт",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable {
                        navController.navigate("screen_3")
                    }
                // Отступ для текстовой ссылки
            )
        }
    }

}

