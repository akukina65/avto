package com.example.myapplication.SignIn

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.SignUp.ResultState
import com.example.myapplication.models.Constant
import com.example.myapplication.models.isEmailValid
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.exception.AuthRestException
import io.github.jan.supabase.auth.providers.builtin.Email
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignInViewModel: ViewModel()
{
    private val state = mutableStateOf(SignInState())
    val uiState:SignInState get() = state.value

    private  val result = MutableStateFlow<ResultState>(ResultState.Initialized)
    val resultState: StateFlow<ResultState> = result.asStateFlow()


    fun updateState(newState: SignInState)
    {
        state.value = newState
        state.value.errorEmail = state.value.email.isEmailValid()
        result.value = ResultState.Initialized
    }

    fun signIn()
    {
        Log.d("SignIn", "signIn() вызвана")
        result.value = ResultState.Loading
        if (state.value.errorEmail)
        {
            viewModelScope.launch {

                try {
                    Log.d("SignIn", "Попытка входа с email: ${state.value.email}") // Логируем email
                    Constant.supabase.auth.signInWith(Email)
                    {
                        email = state.value.email
                        password = state.value.password
                    }
                    Log.d("SignIn", "Success")
                    result.value = ResultState.Success("Success")
                }
                catch (_ex:AuthRestException)
                {
                    Log.d("SignIn", _ex.message.toString())
                    Log.d("SignIn", _ex.errorCode.toString())
                    Log.d("SignIn", _ex.errorDescription.toString())
                    result.value = ResultState.Error(_ex.errorDescription?: "Ошибка получения данных")

                }
            }
        }
        else{
            result.value = ResultState.Error("Ошибка ввода почты")
        }
    }
}
