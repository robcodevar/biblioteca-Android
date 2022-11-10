package com.example.mybooks.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybooks.model.BookItem
import com.example.mybooks.utils.DetailViewState
import com.example.mybooks.utils.ViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


class MainViewModel : ViewModel() {
    private val _viewState = MutableStateFlow<ViewState>(ViewState.Loading)
    private val _detailViewState = MutableStateFlow<DetailViewState>(DetailViewState.Loading)
    val books = _viewState.asStateFlow()
    val booksDetails = _detailViewState.asStateFlow()

// ayuda a formatear el Json
    val format = Json {
        ignoreUnknownKeys = true
        prettyPrint = true
        isLenient = true
    }

    //obtener toda la data de el book.json
    fun getAllBooks(context : Context) = viewModelScope.launch{
        try {
            // lee el archivo Json
            val myJson = context.assets.open("books.json").bufferedReader().use{
                it.readText()
                //Formatea el Json
            }
            val bookList = format.decodeFromString<List<BookItem>>(myJson)
            _viewState.value = ViewState.Success(bookList)

        }catch (e:java.lang.Exception){
            _viewState.value = ViewState.Error(e)
        }
    }

    fun getBookByID(context: Context, isbnNO: String )= viewModelScope.launch {
        try {
            // lee el archivo Json
            val myJson = context.assets.open("books.json").bufferedReader().use{
                it.readText()
            }
            //Formatea el Json
            val bookList = format.decodeFromString<List<BookItem>>(myJson).filter{
                it.isbn.contentEquals(isbnNO)}.first()

            _detailViewState.value = DetailViewState.Success(bookList)

        }catch (e:java.lang.Exception){
            _detailViewState.value = DetailViewState.Error(e)
        }
    }

}