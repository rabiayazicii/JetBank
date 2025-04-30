package com.example.jetbank.data.repository.datasource

import com.example.jetbank.data.model.BankData

interface RemoteDataSource {
    suspend fun getBankDataSource():BankData
}