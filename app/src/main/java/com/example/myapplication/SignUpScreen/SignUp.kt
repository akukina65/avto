package com.example.myapplication.SignUpScreen

import android.util.Log
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myapplication.SignUp.ResultState
import com.example.myapplication.models.isEmailValid
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date

@Composable

fun SignUpScreen (navController: NavController, signUpScreen: SignUpScreen = viewModel())
{
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Регистрация",
            fontWeight = FontWeight.Bold,
            fontSize = 26.sp,
            color = Color.Black,
            modifier = Modifier
                .padding(top = 15.dp, bottom = 80.dp)

        )
    }
    // Здесь можно разместить другие элементы для авторизации
    val uistate = signUpScreen.uiState

    val mContext = LocalContext.current // Получение контекста Android

    val mYear: Int
    val mMonth: Int
    val mDay: Int

    val mCalendar = Calendar.getInstance()

    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()

    val mDate = remember { mutableStateOf("Выберите дату рождения") }
    val resultState by signUpScreen.resultState.collectAsState()
    Column  (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {
        TextFieldStandart(
            value = uistate.surname,
            onValueChange = { signUpScreen.update(uistate.copy(surname = it)) },
            label = "Введите фамилию" // Передаем метку как строку
        )
        Spacer(Modifier.height(10.dp))
        TextFieldStandart(
            value = uistate.name,
            onValueChange = { signUpScreen.update(uistate.copy(name = it)) },
            label = "Введите имя" // Передаем метку как строку
        )

        Spacer(Modifier.height(10.dp))


        TextFieldStandart(
            value = uistate.patronymic,
            onValueChange = { signUpScreen.update(uistate.copy(patronymic = it)) },
            label = "Введите отчество" // Передаем метку как строку
        )

        Spacer(Modifier.height(10.dp))

        TextFieldEmail(uistate.email,uistate.isEmailError) { signUpScreen.update(uistate.copy(email = it)) }
        Spacer(Modifier.height(10.dp))


        TextFieldPassword(uistate.password) { signUpScreen.update(uistate.copy(password = it)) }
        Spacer(Modifier.height(10.dp))

        TextFieldPassword(uistate.confirmPassword) { signUpScreen.update(uistate.copy(confirmPassword = it)) }
        Spacer(Modifier.height(10.dp))
        val mDatePickerDialog = android.app.DatePickerDialog(
            mContext,
            { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
                mDate.value = "$mYear-${mMonth + 1}-$mDayOfMonth"
                signUpScreen.update(uistate.copy(dateBirth = "$mYear-${mMonth + 1}-$mDayOfMonth"))
            }, mYear, mMonth, mDay
        )
        Text(
            text = mDate.value,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable{
                mDatePickerDialog.show()
            }
        )
        Spacer(Modifier.height(20.dp))
        when (resultState) {
            is ResultState.Error -> {
                ButtonNavigation("Зарегистрироваться") { signUpScreen.signup() }
                Text((resultState as ResultState.Error).message)
            }
            is ResultState.Initialized -> {
                ButtonNavigation("Зарегистрироваться") { signUpScreen.signup() }
            }
            ResultState.Loading -> {
                CircularProgressIndicator()
            }
            is ResultState.Success -> {
                navController.navigate("screen_1")
                {
                    popUpTo("screen_1") {
                        inclusive = true
                    }
                }
            }
        }



    }



}