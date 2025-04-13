package com.example.myapplication.Components

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.SignUp.ResultState
import com.example.myapplication.models.Category
import com.example.myapplication.models.cartochka
import com.example.myapplication.models.Constant // Правильный импорт!
//import com.example.myapplication.models.supabase // УДАЛИТЕ ЭТОТ ИМПОРТ!
import io.github.jan.supabase.auth.exception.AuthRestException
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.storage.storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class mainViewModel: ViewModel() {
    private  val _resultState = MutableStateFlow<ResultState>(ResultState.Loading)
    val resultState: StateFlow<ResultState> = _resultState.asStateFlow()

    private  val _card = MutableLiveData<List<cartochka>>()
    val card: LiveData<List<cartochka>> get() = _card

    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>> get() = _categories

    private var allBooks: List<cartochka> = listOf()

    init {
        loadBooks()
        loadCategories()
    }
    private  fun loadBooks(){
        _resultState.value = ResultState.Loading
        viewModelScope.launch {
            try{
                allBooks = Constant.supabase.postgrest.from("cartochka").select().decodeList<cartochka>() // Используйте Constant.supabase
                _card.value = allBooks
                Log.d("MainBooks", "Success")
                Log.d("MainBooks", allBooks.toString())
                _resultState.value = ResultState.Success("Success")
            }
            catch (_ex:AuthRestException)
            {
                Log.d("MainBooks", _ex.message.toString())
                Log.d("MainBooks", _ex.errorCode.toString())
                Log.d("MainBooks", _ex.errorDescription)
                _resultState.value = ResultState.Error(_ex.toString())
            }
        }
    }
    suspend fun getUrlImage(bookName: String): String {
        return withContext(Dispatchers.IO) {
            try {
                val url = Constant.supabase.storage.from("cartochka").publicUrl("${bookName}.png") // Используйте Constant.supabase
                Log.d("buck", url)
                url
            } catch (ex: AuthRestException) {
                Log.e("Error", "Failed to get URL: ${ex.message}")
                ""
            }
        }
    }
    private fun loadCategories() {
        viewModelScope.launch {
            try {
                _categories.value = Constant.supabase.postgrest.from("categories").select().decodeList<Category>() // Используйте Constant.supabase
                Log.d("MainCategories", "Success")
                Log.d("MainCategories", _categories.toString())

            } catch (_ex: AuthRestException) {
                Log.d("Main", _ex.message.toString())
                Log.d("Main", _ex.errorCode.toString())
                Log.d("Main", _ex.errorDescription)
            }
        }
    }
    fun filterList(query: String?, categoryId:Int?)
    {
        val filterCard = allBooks.filter { card ->
            val matchesTitle = query.isNullOrEmpty() || card.title.contains(query, ignoreCase = true)|| card.description.contains(query, ignoreCase = true)
            val matchesCategory = categoryId == -1 || card.categoryId == categoryId
            matchesTitle && matchesCategory}
        _card.value = filterCard
    }
}