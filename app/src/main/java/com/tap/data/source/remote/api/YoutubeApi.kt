package com.tap.data.source.remote.api

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface YoutubeApi {

    @GET("results?")
    suspend fun getSearchTermResults(
        @Query("search_query") searchQuery : String,
    ): String

    @GET("search")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    suspend fun searchVideos(
        @Query("search_query") query: String,
        @Query("hl") language: String = "en",
        @Query("gl") country: String = "US"
    ): String
}

