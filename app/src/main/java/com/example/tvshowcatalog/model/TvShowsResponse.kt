package com.example.tvshowcatalog.model

data class TvShowsResponse(
    val id: Int,
    val image: Image,
    val language: String,
    val name: String,
    val summary: String

)