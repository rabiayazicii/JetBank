package com.example.jetbank.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetbank.common.Resource
import com.example.jetbank.data.domain.usecase.GetBankDataUseCase
import com.example.jetbank.data.model.BankDataItem
import com.example.jetbank.ui.view.Home.HomeScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: GetBankDataUseCase
) :ViewModel(){

    private val _homeState = mutableStateOf(HomeScreenState())
    val homeState: State<HomeScreenState> =_homeState
    private  val _filteredBankList = mutableStateOf<List<BankDataItem>>(emptyList())
    val filteredBankList:State<List<BankDataItem>> = _filteredBankList

    // Eksik olan init bloğu - ViewModel oluşturulduğunda veri yüklemesini başlatır
    init {
        getBankList()
    }

    private fun getBankList()= viewModelScope.launch {
        useCase.invoke().collect{
            when(it){
                is Resource.Error -> {
                    _homeState.value= HomeScreenState(isLoading = false, errorMessage = it.message?:"Error!")
                }
                is Resource.Loading -> {
                    _homeState.value=HomeScreenState(isLoading = true)
                }
                is Resource.Success -> {
                    delay(1000)
                    _homeState.value= HomeScreenState(isLoading = false, bankData = it.data)
                    // Önemli: Yüklenen verileri filtrelenmiş listeye de atayın
                    _filteredBankList.value = it.data ?: emptyList()
                }
            }
        }
    }

    fun filterBankList(query:String){
        if (query.isEmpty()) {
            // Arama sorgusu boşsa tüm listeyi göster
            _filteredBankList.value = _homeState.value.bankData ?: emptyList()
        } else {
            _filteredBankList.value=_homeState.value.bankData?.filter{
                (it.dc_ILCE?.contains(query, ignoreCase = true)==true) ||
                        (it.dc_SEHIR?.contains(query, ignoreCase = true)==true)
            }?: emptyList()
        }
    }
}