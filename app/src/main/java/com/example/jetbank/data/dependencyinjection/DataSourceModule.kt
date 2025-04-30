package com.example.jetbank.data.dependencyinjection

import com.example.jetbank.data.repository.datasource.RemoteDataSource
import com.example.jetbank.data.repository.datasourceImpl.RemoteDataSourceImpl
import com.example.jetbank.data.service.BankAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideDataSource(bankAPIService:BankAPIService):RemoteDataSource{
        return RemoteDataSourceImpl(bankAPIService)
    }
}