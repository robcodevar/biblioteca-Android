package com.example.mybooks.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mybooks.R
import com.example.mybooks.myBooksScreen
import com.example.mybooks.navigation.Screen
import com.example.mybooks.ui.theme.typogra
import com.example.mybooks.utils.coloredShadow
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    val systemUiController = rememberSystemUiController()

    systemUiController.isStatusBarVisible = false // Status bar
    systemUiController.isNavigationBarVisible = false // Navigation bar
    systemUiController.isSystemBarsVisible = false // Status & Navigation bars

    LaunchedEffect(key1 = true ){
        delay(4000)
        navController.popBackStack()
        navController.navigate(Screen.BookList.route)
    }
    Splash()
}

@Composable
fun Splash(){
    val rainbowColorsBrush =
        Brush.sweepGradient(
            listOf(
                Color(0xFF9575CD),
                Color(0xFFBA68C8),
                Color(0xFFE57373),
                Color(0xFFFFB74D),
                Color(0xFFFFF176),
                Color(0xFFAED581),
                Color(0xFF4DD0E1),
                Color(0xFF9575CD)
            )
        )

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
                .border(
                    BorderStroke(4.dp, rainbowColorsBrush),
                    RectangleShape
                ),
            painter = painterResource(id = R.drawable.portada_splash_screen),
            contentDescription = "Portada de diseño saturada",
            contentScale = ContentScale.Crop,
        )
        
        Spacer(modifier = Modifier.height(40.dp))

        Text(text = "Book Aplication",
            color = Color.White,
            textAlign = TextAlign.Center,
            style = typogra.h2,
            modifier = Modifier
                .background(Color.Transparent)
                .coloredShadow(
                    color = Color(0xFF4DD0E1)
                )
        )

    }
}

@Preview(showBackground = true ,
    showSystemUi = true)
@Composable
fun SplashScreenPreview(){
        Splash()
}