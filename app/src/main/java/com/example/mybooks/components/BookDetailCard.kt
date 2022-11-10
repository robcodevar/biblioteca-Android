package com.example.mybooks.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.mybooks.ui.theme.text
import com.example.mybooks.ui.theme.typogra
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun bookDetailCard(
    title: String,
    authors: List<String>,
    thumbnailUrl: String,
    categories: List<String>
) {
                /**
            * CardView
             * - Image
             * - content
            * **/

        Box( Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 20.dp, end = 16.dp, top = 40.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(color = Color.Transparent),
            contentAlignment = Alignment.Center
        ){
            // white box layout
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colors.onSurface)
            )

            // Content
            BookImageContentView(title, authors, thumbnailUrl, categories)
        }
}



@Composable
fun BookImageContentView(
    title: String,
    authors: List<String>,
    thumbnailUrl: String,
    categories: List<String>
) {

    // content
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
            ) {

        AsyncImage(model = thumbnailUrl,
            contentDescription = title,
            modifier = Modifier
                .size(240.dp, 140.dp)
        // no usar offset ni padding
        )

        Spacer(modifier = Modifier.height(16.dp))
        //Contenido:
        Column( modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.onSurface)
            .padding(bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Text(text= title ,
                style = typogra.h6 ,
                textAlign = TextAlign.Center ,
                color = MaterialTheme.colors.primaryVariant
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(text = authors.toString(),
                style =  typogra.caption,
                textAlign = TextAlign.Center,
                color = text.copy(0.7F)
            )

            Spacer(modifier = Modifier.height(12.dp))

            FlowRow() {
                categories.forEach{
                    ChipView(category = it)
                }
            }

        }

    }

}

/**
 * Heybooks is a sample application built to demonstrate the use of Jetpack compose Declarative UI Toolkit with MVVM Architecture for Android 🍄
 *
 * : Insane developer
 */