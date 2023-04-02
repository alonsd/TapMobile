package com.tap.data.repository

interface DashboardRepository {

    suspend fun getSearchTermResults() : String
}