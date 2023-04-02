package com.tap.data.source.remote.source

import com.tap.data.source.remote.api.YoutubeApi
import com.tap.model.Video
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val youtubeApi: YoutubeApi,
) : RemoteDataSource {
    override suspend fun getSearchTermResults(): String {
        val response = youtubeApi.searchVideos("Apple")

        return ""
    }

}