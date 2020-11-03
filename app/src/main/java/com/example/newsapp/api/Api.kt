package com.example.newsapp.api

import com.example.newsapp.model.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface Api {
    @GET("top-headlines")
    @Headers("Cache-Control: no-cache", "X-No-Cache: true")
    fun getNews(
            @Query("country") country: String?,
            @Query("apiKey") apiKey: String?
    ): Call<News?>?

    @GET("everything")
    @Headers("Cache-Control: no-cache", "X-No-Cache: true")
    fun getNewsSearch(
            @Query("q") keyword: String?,
            @Query("language") language: String?,
            @Query("sortBy") sortBy: String?,
            @Query("apiKey") apiKey: String?
    ): Call<News?>?
}