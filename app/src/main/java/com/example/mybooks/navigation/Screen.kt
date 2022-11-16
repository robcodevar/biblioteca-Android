package com.example.mybooks.navigation

import androidx.annotation.StringRes
import com.example.mybooks.R

sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    object BookList : Screen("book_list", R.string.book_list)
    object Details : Screen("book_details", R.string.book_details)
    object Splash : Screen("splash", R.string.splash_description)
}