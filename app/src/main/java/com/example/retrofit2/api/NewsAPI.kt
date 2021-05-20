package com.example.retrofit2.api

import com.example.retrofit2.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("v2/top-headlines")
    suspend fun getNews (
        @Query("country")
        countryCode: String = "ru",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY) : NewsResponse


    @GET("v2/everything")
    suspend fun searchNews (
        @Query("q")
        searchQuery: String,
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY) : Response<NewsResponse>

}