package com.example.mybooks.view

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
fun BookListScreen(viewModel: MainViewModel, actions: MainActions) {

    var libros by remember { mutableStateOf<ViewState>(ViewState.Empty) }
    LaunchedEffect(Unit) {
        libros = viewModel.books.value
    }

    when (val result = libros) {
        ViewState.Loading -> Text(text = "Cargando", color = Color.Black)
        ViewState.Empty -> Text("No se encontraron resultados")
        is ViewState.Error -> Text(
            text = "Error encontrado: ${result.exception}",
            color = Color.Green
        )
        is ViewState.Success -> {
            BookList(result.data, actions)
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun BookList(bookList: List<BookItem>, actions: MainActions) {
    val search = remember {
        mutableStateOf("")
    }
    val listState = rememberLazyListState()

    LazyColumn(
        state = listState,
        contentPadding = PaddingValues(top = 24.dp, bottom = 24.dp),
        modifier = Modifier.background(MaterialTheme.colors.background)
    ) {

        // title
        item {
            Text(
                text = stringResource(id = R.string.text_title),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.primaryVariant,
                maxLines = 2,
                modifier = Modifier.padding(start = 16.dp, end = 24.dp, bottom = 24.dp)
            )
        }
        // search
        item {
            TextInputField(label = stringResource(R.string.books_search),
                value = search.value,
                onValueChanged = {
                    search.value = it
                })
        }
        item {
            Spacer(modifier = Modifier.height(8.dp))
        }
        //search results title
        item {
            Text(
                text = "Famous books",
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.primaryVariant.copy(0.5F),
                textAlign = TextAlign.Start
            )
        }
        // All books list view
        items(bookList.filter { it.title.contains(search.value, ignoreCase = true) }) { book ->
            Log.d("books ", "libros son de tipo ${book.title}")
            ItemBookList(
                book.title,
                book.authors.toString(),
                book.thumbnailUrl,
                book.categories,
                onItemClick = {
                    actions.gotoBookDetails.invoke(book.isbn)
                })
        }

    }
}