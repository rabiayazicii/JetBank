package com.example.jetbank.data.domain.repository

import com.example.jetbank.common.Resource
import com.example.jetbank.data.model.BankData
import kotlinx.coroutines.flow.Flow


interface BankRepository{
    fun getBankDataRepository(): Flow<Resource<BankData>>
}