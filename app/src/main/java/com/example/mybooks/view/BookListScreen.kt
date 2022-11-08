package com.example.mybooks.view

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.mybooks.R
import com.example.mybooks.components.ItemBookList
import com.example.mybooks.components.TextInputField
import com.example.mybooks.model.BookItem
import com.example.mybooks.navigation.MainActions
import com.example.mybooks.utils.ViewState
import com.example.mybooks.viewmodel.MainViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@ExperimentalComposeUiApi
@Composable
fun BookListScreen(viewModel: MainViewModel, actions: MainActions){
    val context = LocalContext.current
    viewModel.getAllBooks(context = context )
    var libros by remember { mutableStateOf<ViewState>(ViewState.Empty) }
    LaunchedEffect( Unit ){
        libros = viewModel.books.value
    }

    when(val result = libros){
        ViewState.Empty -> Text("No se encontraron resultados")
        is ViewState.Error -> Text(text = "Error encontrado: ${result.exception}")
        ViewState.Loading -> Text(text = "Cargando")
        is ViewState.Success -> {
            BookList(result.data, actions)
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun BookList(bookList: List<BookItem>, actions: MainActions){
    val input = remember {
        mutableStateOf("")
    }
    val listState = rememberLazyListState()
    LazyColumn(state = listState ,
                contentPadding = PaddingValues(top = 24.dp , bottom = 24.dp) ){

        // title
        item {
            Text(text = "Explore miles de\nlibros en go",
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.onPrimary,
                maxLines = 2,
                modifier = Modifier.padding(start = 16.dp , end= 20.dp , bottom = 24.dp)
            )
        }
        // search
        item {
            TextInputField(label = stringResource(R.string.books_search) ,
                value = input.value,
                onValueChanged = {
                    input.value = it
                })
        }
        //search results title
        item{
            Text(text = "Famous books",
            style= MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.onPrimary,
            textAlign = TextAlign.Start
            )
        }
        // All books list view
        items(bookList){ book ->
            Log.d("books","books son : ${book.title}")
            ItemBookList(book.title , book.author , book.authors ,book.thumbnailUrl ,book.categories , onItemClick = {
                actions.gotoBookDetails.invoke(book.isbn)
            })
        }

    }
}