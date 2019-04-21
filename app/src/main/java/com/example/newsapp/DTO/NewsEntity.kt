package com.example.newsapp.DTO

import com.google.gson.annotations.SerializedName

data class NewsEntity(

val source: Source,
val author : String,
val title : String,
val description : String,
val url : String,
val urlToImage : String,
val publishedAt : String,
val content:String

)

