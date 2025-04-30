package com.example.jetbank.ui.view.detail

import android.media.Image
import android.text.style.LineBackgroundSpan.Standard
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jetbank.R
import com.example.jetbank.data.model.BankDataItem
import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets



@Composable
fun DetailScreen(bankDataJson:String?) {

    val gson= Gson()
    val context= LocalContext.current
    val bankData=gson.fromJson(
        URLEncoder.encode(bankDataJson,StandardCharsets.UTF_8.toString()),
        BankDataItem::class.java
    )

    Scaffold {paddingValues ->
        Box(
            modifier = Modifier.fillMaxSize().padding(paddingValues), contentAlignment = Alignment.Center
        ){
            BankDetailCard(bankData)
        }
    }

}

@Preview
@Composable
fun BankDetailCard(bankData: BankDataItem= BankDataItem()) {

    Card(modifier = Modifier.padding(16.dp).fillMaxWidth(),
        shape = RoundedCornerShape(30.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()
            .padding(vertical = 30.dp, horizontal = 10.dp),
            verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

            Text(
                text = stringResource(R.string.bank_code),
                style = TextStyle(
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Light
                )
            )
            Text(
                text = "${bankData.dc_BANK_KODU}",
                style = TextStyle(
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
            Text(
                text = stringResource(R.string.postal_code),
                style = TextStyle(
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Light
                )
            )
            Text(
                text = "${bankData.dc_POSTA_KODU}",
                style = TextStyle(
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize =17.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )

            BankCardDetailItem("${bankData.dc_SEHIR} / ${bankData.dc_BANKA_SUBE}",Icons.Default.Home)
            BankCardDetailItem("${bankData.dc_ADRES_ADI}",Icons.Default.ShoppingCart)
            BankCardDetailItem("${bankData.dc_ADRES}",Icons.Default.LocationOn)
            BankCardDetailItem("${bankData.dc_BOLGE_KOORDINATORLUGU}",Icons.Default.Share)
            BankCardDetailItem("${bankData.dc_EN_YAKIM_ATM}",Icons.Default.Create)


        }
    }

}

@Composable
fun BankCardDetailItem(bankItemText:String,
                       bankItemVector:ImageVector){
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
        Icon(imageVector = bankItemVector, contentDescription = null, modifier = Modifier.size(30.dp),
            tint =MaterialTheme.colorScheme.onPrimaryContainer)

        Spacer(modifier = Modifier.width(10.dp))
        Text(text ="${bankItemText}",
            style = TextStyle(
                color = MaterialTheme.colorScheme.secondary,
                fontSize = 17.sp,
                fontWeight = FontWeight.Medium
            ))
    }
}