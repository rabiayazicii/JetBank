package com.example.jetbank.data.dependencyinjection

import com.example.jetbank.data.domain.repository.BankRepository
import com.example.jetbank.data.repository.BankRepositoryImpl
import com.example.jetbank.data.repository.datasource.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideBankRepository(remoteDataSource: RemoteDataSource):BankRepository{
        return BankRepositoryImpl(remoteDataSource)
    }
}

