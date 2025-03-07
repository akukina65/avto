package com.example.myapplication.models

import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from


    val supabase = createSupabaseClient(
        supabaseUrl = "https://youmokksowbzaeufikwr.supabase.co", // Замените на ваш URL
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InlvdW1va2tzb3diemFldWZpa3dyIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDA2NjgzMTgsImV4cCI6MjA1NjI0NDMxOH0.A60-lSYhnL8Bx8WU5Bc-kpoNObqsLU5OgilYnfiNEeQ" // Замените на ваш ключ
    ) {
        install(Postgrest) // Установка Postgrest
        install(Auth)



    }


suspend fun authenticateUser(email: String, password: String): Users? {
    // Валидация email
    if (email.isBlank()) {
        throw IllegalArgumentException("Email не может быть пустым")
    }
    if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        throw IllegalArgumentException("Неверный формат email")
    }

    // Валидация password
    if (password.isBlank()) {
        throw IllegalArgumentException("Пароль не может быть пустым")
    }
    if (password.length < 8) { //Пример минимальной длины пароля
        throw IllegalArgumentException("Пароль должен быть не менее 8 символов")
    }
    //Добавьте другие проверки пароля по необходимости (например, наличие цифр, заглавных букв и т.д.)


    return try {
        val user = supabase.from("Users")
            .select {
                filter { Users::email eq email }
            }
            .decodeList<Users>()
            .firstOrNull()

        // ВАЖНО: Проверяем, что пользователь найден и пароль совпадает (НЕБЕЗОПАСНО!)
        if (user != null && user.password == password) {
            return user
        } else {
            return null // Аутентификация не удалась
        }

    } catch (e: Exception) {
        println("Ошибка при аутентификации: ${e.message}")
        null
    }
}










