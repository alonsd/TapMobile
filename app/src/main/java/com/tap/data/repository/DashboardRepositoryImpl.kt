package com.tap.data.repository

import com.haroldadmin.cnradapter.NetworkResponse
import com.tap.data.source.remote.source.RemoteDataSource
import com.tap.model.YouTubeListItemModel
import com.tap.model.YoutubeSearchResultModel
import javax.inject.Inject

class DashboardRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : DashboardRepository {
    override suspend fun getSearchTermResults(query: String): NetworkResponse<YoutubeSearchResultModel, String> =
        remoteDataSource.searchYoutubeVideos(query)
}

