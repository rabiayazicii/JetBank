package com.example.jetbank.data.service

import com.example.jetbank.data.model.BankData
import retrofit2.http.GET

interface BankAPIService {

    @GET("bankdata")
        suspend fun getBankDataNetwork() : BankData
}