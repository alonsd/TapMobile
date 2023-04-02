package com.tap.data.source.remote.source

interface RemoteDataSource {

    suspend fun getSearchTermResults() : String

}