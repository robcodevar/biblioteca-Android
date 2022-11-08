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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.mybooks.ui.theme.text
import com.example.mybooks.ui.theme.typogra
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun bookDetailCard(){
                /**
            * CardView
             * - Image
             * - content
            * **/

        Box( Modifier
            .wrapContentSize()
            .padding(start = 16 .dp , end = 16.dp)
            .background(color = MaterialTheme.colors.onSurface)
            .clip(RoundedCornerShape(7.dp, 7.dp, 7.dp, 7.dp))
        ){
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                AsyncImage(model = "https://loremflickr.com/320/240",
                    contentDescription = null,
                    modifier = Modifier
                        .size(240.dp, 124.dp)
                        .padding(12.dp)
                        .offset(y = (-30).dp)
                )

            Spacer(modifier = Modifier.height(10.dp))
            //Contenido:
            Column(modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                Text(text = "con Roberto Vargas",
                    style =  typogra.caption,
                    textAlign = TextAlign.Center,
                    color = text.copy(0.7F)
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(text="Más de menos" , style = typogra.subtitle1 , textAlign = TextAlign.Center , color = text )
                Spacer(modifier = Modifier.height(12.dp))
                ChipView("hola jaja")
                FlowRow() {
//                    categories.forEach{ it ->
//                        ChipView(category = it)
//                    }
                }
            }

        }

    }
}
