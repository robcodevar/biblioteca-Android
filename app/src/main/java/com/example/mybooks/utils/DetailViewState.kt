package com.example.mybooks.utils


import com.example.mybooks.model.BookItem

sealed class DetailViewState {
    object Empty : DetailViewState()
    object Loading : DetailViewState()
    data class Success(val data : BookItem) :  DetailViewState()
    data class Error(val exception: Throwable) :  DetailViewState()
}