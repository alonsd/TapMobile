package com.tap.data.source.remote.source

import com.haroldadmin.cnradapter.NetworkResponse
import com.tap.model.YoutubeSearchResultModel

interface RemoteDataSource {

    suspend fun searchYoutubeVideos(query: String): NetworkResponse<YoutubeSearchResultModel, String>

}