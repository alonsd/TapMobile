package com.tap.data.source.remote.api

import com.haroldadmin.cnradapter.NetworkResponse
import com.tap.model.YoutubeSearchResultModel
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface YoutubeApi {

    @GET("youtube/v3/search?part=snippet&maxResults=50")
    suspend fun searchYoutubeVideos(
        @Query("q") searchQuery : String,
        @Query("key") apiKey : String
    ) : NetworkResponse<YoutubeSearchResultModel,String>
}

