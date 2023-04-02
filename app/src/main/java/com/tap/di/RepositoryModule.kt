package com.tap.di

import com.tap.data.repository.DashboardRepository
import com.tap.data.repository.DashboardRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRepository(
        dashboardRepositoryImpl: DashboardRepositoryImpl
    ): DashboardRepository

}