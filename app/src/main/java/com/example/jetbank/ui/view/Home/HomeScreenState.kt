package com.example.jetbank.ui.view.Home

import com.example.jetbank.data.model.BankDataItem

data class HomeScreenState(

    val isLoading:Boolean=false,
    val bankData: ArrayList<BankDataItem>? = null,
    val errorMessage:String?=null
)