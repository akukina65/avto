package com.example.myapplication.SignUpScreen

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import androidx.compose.foundation.layout.size

@Composable
fun ButtonNavigation(label: String, onClick:()-> Unit, ) {
    Button(
        onClick = {
            onClick()
        }, // Устанавливает действие, которое будет выполнено при нажатии на кнопку
        shape = RoundedCornerShape(15.dp), // Устанавливает форму кнопки с закруглёнными углами радиусом
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF008000),
            contentColor = Color.White
        ), //Настраивает цвета кнопки
        modifier = Modifier
            .height(55.dp)
            .defaultMinSize(minWidth = 150.dp)
            .wrapContentWidth() //Эта цепочка модификаторов изменяет внешний вид и расположение кнопки
    ) {
        //Этот композируемый компонент отображает текст на кнопке
        Text(
            label,
            fontSize = 20.sp,
            color = Color.White,

            fontWeight = FontWeight.Bold
        )
    }


}

@Composable
fun TextFieldEmail(value: String, error:Boolean, onvaluechange: (String) -> Unit) {
    androidx.compose.material3.TextField(
        value = value,
        //textStyle = MaterialTheme.typography.displayMedium,
        onValueChange = {
            onvaluechange(it)
        },
        label = { Text("Введите почту") },


        isError = !error,


        colors = TextFieldDefaults.colors(
            unfocusedContainerColor =  Color.LightGray,
            unfocusedTextColor = Color.Black,
            focusedContainerColor =  Color.LightGray,
            focusedTextColor = Color.Black,
            focusedIndicatorColor = Color(0xFF008000),
            unfocusedIndicatorColor = Color(0xFF008000),
            disabledIndicatorColor = Color(0xFF008000),
            errorPlaceholderColor = Color.Red
        ),

        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .width(350.dp) // Установка фиксированной ширины
            .padding(bottom = 16.dp)
    )
}

@Composable
fun TextFieldStandart(
    value: String,
    onValueChange: (String) -> Unit,
    label: String // Новый параметр для метки
) {
    androidx.compose.material3.TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) }, // Используем переданную метку
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.LightGray, // Цвет фона, когда поле не в фокусе
            unfocusedTextColor = Color.Black, // Цвет текста, когда поле не в фокусе
            focusedContainerColor = Color.LightGray, // Цвет фона, когда поле в фокусе
            focusedTextColor = Color(0xFF0000FF), // Установите нужный цвет текста при фокусе
            focusedIndicatorColor = Color(0xFF008000), // Цвет индикатора при фокусе
            unfocusedIndicatorColor = Color(0xFF008000), // Цвет индикатора при отсутствии фокуса
            disabledIndicatorColor = Color(0xFF008000) // Цвет индикатора, когда поле отключено
        ),
        shape = RoundedCornerShape(5.dp), // Уголки текстового поля
        modifier = Modifier
            .width(350.dp) // Установка фиксированной ширины
            .padding(bottom = 16.dp) // Отступ снизу
    )
}


@Composable
fun TextFieldPassword(value: String, onvaluechange: (String) -> Unit) {
    var passwordVisibility by remember { mutableStateOf(false) }
    androidx.compose.material3.TextField(
        value = value,
        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(onClick = {
                passwordVisibility = !passwordVisibility
            }) {
                if (passwordVisibility) {
                    Icon(
                        painter = painterResource(id = R.drawable.open),
                        contentDescription = "",
                        modifier = Modifier.size(30.dp)
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.close),
                        contentDescription = "",
                                modifier = Modifier.size(24.dp)
                    )
                }
            }
        },
        onValueChange = { onvaluechange(it) },
        label = { Text("Введите пароль") },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.LightGray,
            unfocusedTextColor = Color.Black,
            focusedContainerColor = Color.LightGray,
            focusedTextColor = Color.Black,
            focusedIndicatorColor = Color(0xFF008000),
            unfocusedIndicatorColor = Color(0xFF008000),
            disabledIndicatorColor = Color(0xFF008000)

        ),
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .width(350.dp) // Установка фиксированной ширины
            .padding(bottom = 16.dp)
    )
}
