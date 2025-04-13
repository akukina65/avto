package com.example.myapplication.Components

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.SignUp.ResultState
import com.example.myapplication.models.Category
import com.example.myapplication.models.Constant // Импортируем Constant
import com.example.myapplication.models.cartochka
import com.example.myapplication.models.supabase
import com.example.myapplication.state.cartochkaState
import io.github.jan.supabase.auth.exception.AuthRestException
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.storage.storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class cardViewModel(id:String):ViewModel() {
    var idcard = id
    private val _resultStateUpdate = MutableStateFlow<ResultState>(ResultState.Loading)
    val resultStateUpdate: StateFlow<ResultState> = _resultStateUpdate.asStateFlow()
    private val _resultStateDelete = MutableStateFlow<ResultState>(ResultState.Initialized)
    val resultStateDelete: StateFlow<ResultState> = _resultStateDelete.asStateFlow()

    private val _resultStateUpload = MutableStateFlow<ResultState>(ResultState.Initialized)
    val resultStateUpload: StateFlow<ResultState> = _resultStateUpload.asStateFlow()

    lateinit var card: cartochka

    private  val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>> get() = _categories

    fun updateState(newState:cartochkaState){
        _state.value = newState
    }
    private  val _state = mutableStateOf(cartochkaState())
    val state:cartochkaState get() = _state.value
    val supabase = Constant.supabase // Используем Constant.supabase!
    init {
        loadCategories()
        getBook()
    }

    fun getBook()
    {
        _resultStateUpload.value = ResultState.Loading
        viewModelScope.launch {
            try{
                card = supabase.postgrest.from("cartochka").select(){
                    filter {
                        eq("id", idcard)
                    }
                }.decodeSingle<cartochka>()

                _state.value = cartochkaState(

                    id = card.id,
                    title = card.title,
                    category = card.categoryId,
                    description =  card.description,
                    mesto = card.mesto,
                    bonus = card.bonus
                )
                _resultStateUpload.value = ResultState.Success("Success")
            }
            catch (e: AuthRestException)
            {
                Log.e("getBook", "Error loading data", e)
                _resultStateUpload.value = ResultState.Error(e.message.toString())
            }
        }
    }
    fun updateBook(){
        Log.d("updateBook", "Starting updateBook()")
        _resultStateUpdate.value = ResultState.Loading
        viewModelScope.launch {
            try{
                supabase.postgrest.from("cartochka").update(

                    {
                        set("title", _state.value.title)
                        set("categoryId", _state.value.category)
                        set ("description", _state.value.description)
                        set("mesto", _state.value.mesto)
                        set("bonus", _state.value.bonus)
                    }
                )
                {
                    filter {
                        eq("id", idcard)
                    }
                }
                _resultStateUpdate.value = ResultState.Success("Success")
            }
            catch (e: AuthRestException){
                Log.e("updatecard", "Error update data", e)
                _resultStateUpdate.value = ResultState.Error(e.message.toString())
            }
        }
    }

    fun deleteBook()
    {
        Log.d("deleteBook", "Starting deleteBook()")
        _resultStateDelete.value = ResultState.Loading
        viewModelScope.launch {
            try{
                supabase.postgrest.from("cartochka").delete(

                ){
                    filter {
                        eq("id", idcard)
                    }
                }
                _resultStateDelete.value = ResultState.Success("Delete")
            }
            catch (e:AuthRestException){
                Log.e("deletecard", "Error delete data", e)
                _resultStateDelete.value = ResultState.Error(e.message.toString())
            }
        }
    }
    private fun loadCategories()
    {
        viewModelScope.launch {
            try{
                _categories.value = supabase.postgrest.from("categories").select().decodeList<Category>()
                Log.d("loadCategories", "Success")
                Log.d("loadCategories", _categories.toString())
            }
            catch (_ex:AuthRestException)
            {
                Log.d("loadCategories", _ex.message.toString())
                Log.d("loadCategories", _ex.errorCode.toString())
                Log.d("loadCategories", _ex.errorDescription)
            }
        }
    }
    suspend fun geturlImage(bookname:String):String
    {
        return withContext(Dispatchers.IO){
            try{
                val url = supabase.storage.from("cartochka").publicUrl("${bookname}.png")
                Log.d("buck", url)
                url
            }
            catch (ex:AuthRestException){
                Log.e("error", "Failed to get URL: ${ex.message}")
                ""
            }
        }
    }
}

