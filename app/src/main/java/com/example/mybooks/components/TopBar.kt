package com.example.mybooks.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mybooks.R
import com.example.mybooks.ui.theme.typogra

@Composable
fun topBar(title:String){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = Icons.Default.ArrowBack ,
            contentDescription = stringResource(R.string.texto_backbutton)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = title,
            style = typogra.h5 ,
            color = MaterialTheme.colors.primaryVariant
        )

    }
}


