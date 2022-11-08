package com.example.mybooks.model

import kotlinx.serialization.Serializable

@Serializable
data class BookItem(
     @Transient val authors: List<String> = emptyList(),
     @Transient val categories: List<String> = emptyList(),
     @Transient val isbn: String = "",
     @Transient val longDescription: String = "",
     @Transient val pageCount: Int = 0,
     @Transient val publishedDate: String = "",
     @Transient val shortDescription: String = "",
     @Transient val status: String = "",
     @Transient val thumbnailUrl: String= "" ,
      val title: String= "",
      val author: String= ""
)