package com.tap.data.repository

import com.haroldadmin.cnradapter.NetworkResponse
import com.tap.model.YoutubeSearchResultModel

interface DashboardRepository {

    suspend fun getSearchTermResults(query: String) : NetworkResponse<YoutubeSearchResultModel, String>
}