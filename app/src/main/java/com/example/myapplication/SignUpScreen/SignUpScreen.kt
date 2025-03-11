package com.example.myapplication.SignUpScreen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.SignUp.ResultState
import com.example.myapplication.SignUp.SignUp
import com.example.myapplication.models.Profile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.myapplication.models.isEmailValid
import com.example.myapplication.models.supabase
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.exception.AuthRestException
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch

class SignUpScreen:ViewModel() {
    private  val state = mutableStateOf(SignUp())
    val uiState:SignUp get() = state.value
    private val  result = MutableStateFlow<ResultState>(ResultState.Initialized)
    val resultState: StateFlow<ResultState> = result.asStateFlow()

    fun update (newState: SignUp)
    {
        state.value = newState
        state.value.isEmailError = state.value.email.isEmailValid()
        result.value = ResultState.Initialized
    }

    fun signup()
    {
        result.value = ResultState.Loading
        if (state.value.isEmailError && state.value.password == state.value.confirmPassword)
        {
            viewModelScope.launch {
                try{
                    supabase.auth.signUpWith(Email)
                    {
                        email = state.value.email
                        password = state.value.password
                    }
                    Log.d("SignUp", "Success")
                    val user = Profile(state.value.surname,
                        state.value.name, state.value.patronymic, state.value.dateBirth, null)
                    supabase.from("Profil").insert(user)
                    result.value = ResultState.Success("Success")

                }
                catch (_ex:AuthRestException)
                {
                    Log.d("signup" ,_ex.message.toString())
                    Log.d("signUp", _ex.errorCode.toString())
                    Log.d("signUp", _ex.errorDescription.toString())
                    result.value = ResultState.Error(_ex.errorDescription?: "Ошибка получения данных")
                }
            }
        }
        else
        {
            result.value = ResultState.Error("Ошибка ввода почты")
        }
    }
}
