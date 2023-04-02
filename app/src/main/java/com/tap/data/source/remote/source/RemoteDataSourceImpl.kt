package com.tap.data.source.remote.source

import com.haroldadmin.cnradapter.NetworkResponse
import com.tap.core.constants.NetworkConstants
import com.tap.data.source.remote.api.YoutubeApi
import com.tap.model.YoutubeSearchResultModel
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val youtubeApi: YoutubeApi,
) : RemoteDataSource {

    override suspend fun searchYoutubeVideos(query: String): NetworkResponse<YoutubeSearchResultModel, String> {
        return youtubeApi.searchYoutubeVideos(query, NetworkConstants.API_KEY)
    }


}