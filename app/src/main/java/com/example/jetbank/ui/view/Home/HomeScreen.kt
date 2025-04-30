package com.example.jetbank.ui.view.Home

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.widget.Space
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.example.jetbank.R
import com.example.jetbank.data.domain.usecase.GetBankDataUseCase
import com.example.jetbank.data.model.BankDataItem
import com.example.jetbank.ui.component.ErrorComponent
import com.example.jetbank.ui.component.LoadingAnimation
import com.example.jetbank.ui.component.NoDataImage
import com.example.jetbank.ui.navigation.AppScreen
import com.example.jetbank.ui.viewmodel.HomeViewModel
import com.google.gson.Gson
import org.intellij.lang.annotations.Language
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.util.Locale



@Composable
fun HomeScreen(navController: NavController){

    val viewModel:HomeViewModel= hiltViewModel()
    val context= LocalContext.current
    val gson= Gson()

    BackHandler {
        (context as? Activity)?.finish()
    }


    val state=viewModel.homeState.value
    val filteredBankData=viewModel.filteredBankList.value
    val searchQuery = remember { mutableStateOf("") }

    val currentNavController= rememberUpdatedState(navController)
    val flagTR= painterResource(R.drawable.tr)
    val flagUS= painterResource(R.drawable.en)


    Scaffold(
        topBar = {
            Column {
                TextField(
                    value = searchQuery.value,
                    onValueChange = { newValue ->
                        searchQuery.value = newValue
                        viewModel.filterBankList(newValue)
                    },
                    label = { Text(stringResource(R.string.search_by_city)) },
                    modifier = Modifier
                        .padding(vertical = 30.dp, horizontal = 20.dp)
                        .fillMaxWidth()
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp, horizontal = 10.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Image(
                        painter = flagUS,
                        contentDescription = "Change language to English",
                        modifier = Modifier
                            .clickable { changeLanguage(context, currentNavController, "en") }
                            .size(100.dp)

                    )
                    Image(
                        painter = flagTR,
                        contentDescription = "Change language to Turkish",
                        modifier = Modifier
                            .clickable { changeLanguage(context, currentNavController, "tr") }
                            .size(100.dp)
                    )
                }
            }
        },
        content = { paddingValue ->

            if (state.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    LoadingAnimation()
                }
            } else {
                if (!state.errorMessage.isNullOrEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        ErrorComponent()
                    }
                }
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValue)
            ) {
                if (filteredBankData.isEmpty()) {
                    item {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                if(!state.isLoading) {
                                    NoDataImage()
                                }
                            }
                        }
                    }
                } else {
                    items(filteredBankData) { bankItem ->
                        BankDataCard(bankItem = bankItem) {
                            val bankDataJson = gson.toJson(bankItem)
                            val encodedBankDataJson = URLEncoder.encode(bankDataJson, StandardCharsets.UTF_8.toString())
                            navController.navigate("${AppScreen.DETAİL_SCREEN.name}/$encodedBankDataJson")
                        }
                    }
                }
            }
        }
    )

}

@Preview
@Composable
fun BankDataCard(
    bankItem: BankDataItem = BankDataItem(),
    onClickToCard: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .padding(vertical = 10.dp, horizontal = 20.dp)
            .clickable(onClick = onClickToCard)
    ) {
        Column(modifier = Modifier.padding(15.dp)) {
            if (bankItem.dc_BANKA_SUBE.isNullOrEmpty()) {
                BankDataCardItem(Icons.Default.LocationOn, "${bankItem.dc_BANKA_SUBE} ŞUBESİ/${bankItem.dc_SEHIR}")
            } else {
                BankDataCardItem(Icons.Default.LocationOn, bankItem.dc_BANKA_SUBE)
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "${bankItem.dc_ADRES}",
                style = TextStyle(
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Italic
                )
            )
        }
    }
}

@Composable
fun BankDataCardItem(
    bankItemVector: ImageVector,
    bankItemText: String?
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = bankItemVector,
            modifier = Modifier.size(30.dp),
            tint = MaterialTheme.colorScheme.onPrimaryContainer,
            contentDescription = ""
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = "${bankItemText}",
            style = TextStyle(
                color = MaterialTheme.colorScheme.secondary,
                fontSize = 17.sp,
                fontWeight = FontWeight.Medium
            )
        )
    }
    Spacer(modifier = Modifier.height(5.dp))
}

private fun changeLanguage(
    context: Context,
    currentNavController: State<NavController>,
    language: String
) {
    val locale = Locale(language)
    Locale.setDefault(locale)
    val config = Configuration()
    config.setLocale(locale)
    context.resources.updateConfiguration(config, context.resources.displayMetrics);

    // Force UI recreation
    val currentDestination = currentNavController.value.currentDestination?.id
    currentNavController.value.popBackStack()
    currentDestination?.let { currentNavController.value.navigate(it) }
}