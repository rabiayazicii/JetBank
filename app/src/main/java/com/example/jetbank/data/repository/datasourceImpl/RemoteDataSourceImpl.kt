package com.example.jetbank.data.repository.datasourceImpl

import android.net.Network
import com.example.jetbank.data.model.BankData
import com.example.jetbank.data.repository.datasource.RemoteDataSource
import com.example.jetbank.data.service.BankAPIService
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val bankAPIService: BankAPIService) :RemoteDataSource{
    override suspend fun getBankDataSource():BankData{

        return bankAPIService.getBankDataNetwork()
    }
}