package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import com.example.myapplication.models.Constant.supabase
import kotlinx.serialization.Serializable

import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateListOf


import com.example.myapplication.function.Navigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                //Navigation()

                Surface(modifier = Modifier.fillMaxSize()) {
                    //UserList()
                    // UI код здесь, если есть необходимость
                    //Navigation()
//                    HorizontalGradientBackgroundExample()
//                    MyApp()

                    Navigation()

                }
            }
        }


    }


//    val supabase = createSupabaseClient(
//        supabaseUrl = "https://youmokksowbzaeufikwr.supabase.co", // Замените на ваш URL
//        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InlvdW1va2tzb3diemFldWZpa3dyIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDA2NjgzMTgsImV4cCI6MjA1NjI0NDMxOH0.A60-lSYhnL8Bx8WU5Bc-kpoNObqsLU5OgilYnfiNEeQ" // Замените на ваш ключ
//    ) {
//        install(Postgrest) // Установка Postgrest
//        install(Auth)
//
//
//
//    }



//    @Composable
//    fun MyApp() {
//        var isLoggedIn by remember { mutableStateOf(false) }
//        val navController = rememberNavController()
//
//        if (isLoggedIn) {
//            // SecondScreen() // Замените на ваш экран после входа
//            MyButtons1(onClick = { /*TODO*/ })
//        } else {
//            MyButtons(
//                navController = navController,
//                onLoginSuccess = { isLoggedIn = true }, // Функция обратного вызова при успехе
//                authenticateUser = ::authenticateUser
//            )
//        }
//    }


}
@Serializable
data class Users(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val middleName: String,
    val email: String,
    val password: String,
    val birthDate: String
)

@Composable
fun UserList()
{
    val users = remember { mutableStateListOf<Users>()}
    LaunchedEffect (Unit){ withContext(Dispatchers.IO){
        val result = supabase.from("Users").select().decodeList<Users>()
            users.addAll(result)

    }
        }


    LazyColumn {
        items(users) { userItem ->
            ListItem(headlineContent = { Text(text = userItem.email) }

            )

    }
//    var newUser by remember { mutableStateOf("") }
//    val composableScope = rememberCoroutineScope()
//    Row {
//        OutlinedTextField(value = newUser, onValueChange = { newUser = it })
//        Button(onClick = {
//            composableScope.launch(Dispatchers.IO) {
//                val user = supabase.from("Users").insert(mapOf("email" to newUser)) {
//                    select()
//                    single()
//
//                }.decodeAs<Users>()
//                users.add(user)
//                newUser = ""
//            }
//        }) {
//            Text("Save")
//        }
//    }
}
}

//@Composable
//fun LoginScreen(onLoginSuccess: () -> Unit) { // Добавляем функцию обратного вызова при успешной аутентификации
//    var email by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
//    var errorMessage by remember { mutableStateOf("") }
//    val coroutineScope = rememberCoroutineScope()  // Используем корутину для сетевых запросов
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//    ) {
//        TextField(
//            value = email,
//            onValueChange = { email = it },
//            label = { Text("Введите почту") },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(bottom = 16.dp)
//        )
//
//        TextField(
//            value = password,
//            onValueChange = { password = it },
//            label = { Text("Введите пароль") },
//            visualTransformation = PasswordVisualTransformation(),
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(bottom = 16.dp)
//        )
//
//        Button(
//            onClick = {
//                coroutineScope.launch {
//                    val user = authenticateUser(email, password)
//                    if (user != null) {
//                        // Успешная аутентификация
//                        errorMessage = "" // Очищаем сообщение об ошибке, если оно было
//                        onLoginSuccess() // Вызываем функцию обратного вызова
//                    } else {
//                        // Неверные учетные данные
//                        errorMessage = "Неверная почта или пароль"
//                    }
//                }
//            },
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text("Войти")
//        }
//
//        if (errorMessage.isNotEmpty()) {
//            Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
//        }
//    }
//}

//suspend fun authenticateUser(email: String, password: String): Users? {
//    return withContext(Dispatchers.IO) {
//        try {
//            val users = supabase.from("users")
//                .select {
//                    filter { Users::email eq email }
//                    filter { Users::pasword eq password }
//                }.decodeList<Users>()
//
//            if (users.isNotEmpty()) {
//                users.firstOrNull() // Возвращаем пользователя, если найден
//            } else {
//                null // Пользователь не найден
//            }
//        } catch (e: Exception) {
//            println("Ошибка при аутентификации: ${e.message}")
//            null // Ошибка при запросе к базе данных
//        }
//    }
//}
//@Composable
//fun MyApp() {
//    var isLoggedIn by remember { mutableStateOf(false) }
//    val navController = rememberNavController()
//
//    if (isLoggedIn) {
//        Butt2() // Замените на ваш экран после входа
//        MyButtons1(onClick = { /*TODO*/ })
//    } else {
//        MyButtons(
//            navController = navController,
//            onLoginSuccess = { isLoggedIn = true }, // Функция обратного вызова при успехе
//            authenticateUser = ::authenticateUser
//        )
//    }
//}