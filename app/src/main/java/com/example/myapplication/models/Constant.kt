package com.example.myapplication.models

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest


object Constant {

        val supabase = createSupabaseClient(
            supabaseUrl = "https://youmokksowbzaeufikwr.supabase.co", // Замените на ваш URL
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InlvdW1va2tzb3diemFldWZpa3dyIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDA2NjgzMTgsImV4cCI6MjA1NjI0NDMxOH0.A60-lSYhnL8Bx8WU5Bc-kpoNObqsLU5OgilYnfiNEeQ" // Замените на ваш ключ
        ) {
            install(Postgrest) // Установка Postgrest
            install(Auth)



        }

}