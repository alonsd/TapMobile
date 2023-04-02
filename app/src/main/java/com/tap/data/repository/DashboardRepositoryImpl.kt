package com.tap.data.repository

import com.tap.data.source.remote.source.RemoteDataSource
import javax.inject.Inject

class DashboardRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : DashboardRepository {
    override suspend fun getSearchTermResults(): String = remoteDataSource.getSearchTermResults()

}

