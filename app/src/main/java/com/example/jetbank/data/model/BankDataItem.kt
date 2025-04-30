package com.example.jetbank.data.model

import com.google.gson.annotations.SerializedName

data class BankDataItem(
    @SerializedName("ID")
    val ID: Int?=null,

    @SerializedName("dc_ADRES")
    val dc_ADRES: String?=null,

    @SerializedName("dc_ADRES_ADI")
    val dc_ADRES_ADI: String?=null,

    @SerializedName("dc_BANKA_SUBE")
    val dc_BANKA_SUBE: String?=null,

    @SerializedName("dc_BANKA_TIPI")
    val dc_BANKA_TIPI: String?=null,

    @SerializedName("dc_BANKA_KODU")
    val dc_BANK_KODU: String?=null,

    @SerializedName("dc_BOLGE_KOORDINATORLUGU")
    val dc_BOLGE_KOORDINATORLUGU: String?=null,

    @SerializedName("dc_EN_YAKIN_ATM")
    val dc_EN_YAKIM_ATM: String?=null,

    @SerializedName("dc_ILCE")
    val dc_ILCE: String?=null,

    @SerializedName("dc_ON_OFF_LINE")
    val dc_ON_OFF_LINE: String?=null,

    @SerializedName("dc_ON_OFF_SITE")
    val dc_ON_OFF_SITE: String?=null,

    @SerializedName("dc_POSTA_KODU")
    val dc_POSTA_KODU: String?=null,

    @SerializedName("dc_SEHIR")
    val dc_SEHIR: String?=null
)