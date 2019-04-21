package com.example.newsapp.DTO

import com.example.newsapp.DTO.NewsEntity

data class Response (
    val status : String,
    val totalResults : String,
    val articles : List<NewsEntity>

)