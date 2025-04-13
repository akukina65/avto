package com.example.myapplication.models

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage


object Constant {

        val supabase = createSupabaseClient(
            supabaseUrl = "https://tmzsltvdankxnszuzpfs.supabase.co", // Замените на ваш URL
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InRtenNsdHZkYW5reG5zenV6cGZzIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDE1NTI1MTQsImV4cCI6MjA1NzEyODUxNH0.hGGzLHAT5lf6PVKXXaWcLLzQXNun1Hoyg22YVgFFniM" // Замените на ваш ключ
        ) {
            install(Postgrest) // Установка Postgrest
            install(Auth)
            install(Storage)



        }

}