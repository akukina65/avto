package com.example.myapplication.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*

import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import androidx.navigation.compose.rememberNavController








import androidx.compose.material3.TextField // Импортируйте нужный TextField
import androidx.compose.material3.Text // Импортируйте Text

import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel


import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import com.example.myapplication.models.User

import com.example.myapplication.models.Users
import com.example.myapplication.models.authenticateUser
import com.example.myapplication.models.supabase
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.delay

import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@Composable
fun MyButtons(
    navController: NavController,
    authViewModel: AuthViewModel,
    authenticateUser: suspend (String, String) -> Users?
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
            Text(
                text = "Авторизация",
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                color = Color.Black,
                modifier = Modifier
                    .padding(top = 15.dp, bottom = 80.dp)
                    .align(Alignment.CenterHorizontally)// Отступ снизу
            )
            // Здесь можно разместить другие элементы для авторизации




        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Введите почту") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Введите пароль") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading) {
            CircularProgressIndicator()
        }

        if (errorMessage.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp)) //Уменьшил отступ
            Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
        }

        val greenButtonColors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF008000),
            contentColor = Color.White
        )

        Button(
            onClick = {
                coroutineScope.launch {
                    isLoading = true
                    try {
                        val user = authenticateUser(email, password)
                        isLoading = false
                        if (user != null) {
                            errorMessage = ""
                            authViewModel.isLoggedIn = true
                            navController.navigate("screen_2")
                        } else {
                            errorMessage = "Неверная почта или пароль"
                        }
                    } catch (e: IllegalArgumentException) {
                        isLoading = false
                        errorMessage = e.message ?: "Ошибка валидации данных" // Отображаем сообщение об ошибке
                    } catch (e: Exception) {
                        isLoading = false
                        errorMessage = "Неизвестная ошибка: ${e.message}" // Отображаем общую ошибку
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth() //Кнопка на всю ширину
                .height(40.dp),
            colors = greenButtonColors
        ) {
            Text(text = "Войти")
        }
    }
}


@Composable
fun MyButtons1(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Логин",
            modifier = Modifier.padding(start = 20.dp, top = 118.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            color = Color.Black
        )
        Text(
            text = "Пароль",
            modifier = Modifier.padding(start = 20.dp, top = 67.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(160.dp))

        // Используем Box для центровки ссылок
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.Center // Центрируем содержимое
        ) {
            Text(
                text = "Создать аккаунт",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable {
                        navController.navigate("registration")
                    }
                    // Отступ для текстовой ссылки
            )
        }
    }
}




@Serializable
data class User(
    val id: Int,
    val name: String,
    val surname: String,
    val patronymic: String,
    val email: String,
    val password: String,

)



@Composable
fun Butt2()
{

}

@Composable
fun Regestra(navController: NavController, authViewModel: AuthViewModel) {
    var surname by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var patronymic by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var snackbarVisible by remember { mutableStateOf(false) } // состояние видимости snackbar
    var snackbarMessage by remember { mutableStateOf("") } // сообщение для snackbar

    val composableScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Регистрация", style = MaterialTheme.typography.titleLarge)

        // Поля ввода
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Имя") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = surname,
            onValueChange = { surname = it },
            label = { Text("Фамилия") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = patronymic,
            onValueChange = { patronymic = it },
            label = { Text("Отчество") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Электронная почта") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Пароль") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        Button(
            onClick = {
                isLoading = true
                composableScope.launch {
                    val newUserData = mapOf(
                        "name" to name,
                        "surname" to surname,
                        "patronymic" to patronymic,
                        "email" to email,
                        "password" to password
                    )

                    try {
                        // Отправка данных на сервер в IO потоке
                        val response = withContext(Dispatchers.IO) {
                            supabase.from("Users").insert(newUserData)
                        }

                        // Переход на следующий экран в основном потоке
                        if (response.data.isNotEmpty()) {
                            authViewModel.isLoggedIn = true
                            snackbarMessage = "Вы успешно зарегистрировались."
                            snackbarVisible = true
                            // Ждем, чтобы дисплей не перешел сразу на следующий экран
                            delay(2000) // ПоказываемSnackbar в течение 2 секунд
                            navController.navigate("screen_1") {
                                popUpTo("registration") { inclusive = true }
                            }
                        }
                    } catch (e: Exception) {
                        // Ошибки не выводятся, но можно оставить их для отладки, если потребуется
                    } finally {
                        isLoading = false
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading
        ) {
            Text("Зарегистрироваться")
        }

        // Индикатор загрузки
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.padding(top = 16.dp))
        }
    }

    // Snackbar для отображения сообщений
    if (snackbarVisible) {
        Snackbar(
            action = {
                Button(onClick = { snackbarVisible = false }) {
                    Text("Закрыть")
                }
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(snackbarMessage)
        }
    }
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



