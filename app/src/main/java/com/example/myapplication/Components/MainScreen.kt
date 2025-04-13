package com.example.myapplication.Components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myapplication.SignUp.ResultState
import com.example.myapplication.SignUpScreen.TextFieldSearch
import com.example.myapplication.state.Cart
import com.example.myapplication.state.CategoryItem
import kotlinx.coroutines.runBlocking

@Composable

fun MainScreen (navController: NavController, mainViewModel: mainViewModel = viewModel())
{
    val textSearch = remember { mutableStateOf("") }

    val categories = mainViewModel.categories.observeAsState(emptyList())

    val card = mainViewModel.card.observeAsState(emptyList())

    val selectedCategory = remember { mutableIntStateOf(-1) }

    val resultState by mainViewModel.resultState.collectAsState()

    Column (
        modifier =  Modifier
            .fillMaxSize()
            .padding(top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    )
    {
        TextFieldSearch(
            value = textSearch.value,
            onvaluechange = { newText -> // Обработчик изменения текста
                textSearch.value = newText // Обновляем состояние текста поиска
                mainViewModel.filterList(
                    newText,
                    selectedCategory.intValue
                ) // Фильтр
    }
        )
        when (resultState){
            is ResultState.Error ->
                Text(text = (resultState as ResultState.Error).message)
            ResultState.Initialized -> TODO()
            ResultState.Loading -> {
                Box(
                    modifier =  Modifier

                        .size(100.dp)
                        .clip(RoundedCornerShape(8.dp)),



                ){
                    CircularProgressIndicator()
                }
            }
            is ResultState.Success ->{
                LazyRow {
                    items(categories.value.indices.toList()) {
                        index -> CategoryItem(
                            category = categories.value[index].name,
                            isSelected =  selectedCategory.intValue == categories.value[index].id,
                            onClick = {
                                selectedCategory.intValue = categories.value[index].id
                                mainViewModel.filterList(
                                    textSearch.value,
                                    selectedCategory.intValue
                                )
                            }
                        )
                    }
                }
                LazyColumn {
                    items(card.value){it ->
                      Cart(cart = it, getUrl = {
                          runBlocking {
                              mainViewModel.getUrlImage(it)
                          }
                      }, onClick = {
                          navController.navigate("detailsbook" + "/" + it.id)
                      })
                    }
                }
            }

        }        }


}