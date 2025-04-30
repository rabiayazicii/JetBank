package com.example.jetbank.data.repository

import com.example.jetbank.common.Resource
import com.example.jetbank.data.domain.repository.BankRepository
import com.example.jetbank.data.model.BankData
import com.example.jetbank.data.repository.datasource.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BankRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) :BankRepository{
    override fun getBankDataRepository(): Flow<Resource<BankData>> = flow{
        emit(Resource.Loading())
        val result = runCatching {
            remoteDataSource.getBankDataSource()
        }.onFailure {
            emit(Resource.Error(it.message ?: "Error"))
        }.getOrNull()

        result?.let {
            emit(Resource.Success(it))
        }
    }

}