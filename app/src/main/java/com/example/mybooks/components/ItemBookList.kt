package com.example.mybooks.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mybooks.ui.theme.primary
import com.example.mybooks.ui.theme.typogra
import com.example.mybooks.utils.coloredShadow
import com.google.accompanist.flowlayout.FlowRow


@Composable
fun ItemBookList(
    title: String,
    author: String,
    thumbnailUrl: String,
    categories: List<String>,
    onItemClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .clickable(onClick = onItemClick)
            .wrapContentHeight()
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(MaterialTheme.colors.background)
            .coloredShadow(color = MaterialTheme.colors.primary,
                alpha = 0.050F ,
                shadowRadius = 4.dp,
                borderRadius = 0.dp,
                offsetX = 0.dp,
                offsetY = 4.dp)
            .padding(12.dp)

    ){

        // Row = Image + content
        Row(modifier = Modifier.fillMaxWidth().background(MaterialTheme.colors.onSurface) ,
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(thumbnailUrl)
                    .crossfade(2000)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .clip(RoundedCornerShape(15.dp))
                    .size(98.dp, 145.dp)
                    .background(Color.White)
                    .padding(12.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))
            // Content
            Column {
                Text(text = "by : ".plus(author), style = typogra.caption, color = MaterialTheme.colors.primaryVariant)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = title, style = typogra.subtitle1, color =  MaterialTheme.colors.primaryVariant)
                Spacer(modifier = Modifier.height(12.dp))
                FlowRow() {
                    categories.forEach{ it ->
                        ChipView(category = it)
                    }
                }

            }
        }
    }
}

@Composable
fun ChipView(category: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(primary.copy(.10F))
            .padding(start = 12.dp, end = 12.dp, top = 5.dp, bottom = 5.dp),
            contentAlignment = Alignment.Center
        ){
        Text( text = category , style = typogra.caption, color = primary)
    }
}

