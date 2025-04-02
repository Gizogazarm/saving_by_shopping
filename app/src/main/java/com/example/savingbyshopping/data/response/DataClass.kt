package com.example.savingbyshopping.data.response

import android.os.Parcelable
import com.example.savingbyshopping.utils.JenisProduk
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemShop(
    val namaItem : String,
    val jenisProduk: JenisProduk,
    val hargaAsli : Long,
    val hargaDiskon : Long,
    val quantity : Int,
    val totalHarga : Long,
    val saveDiskon : Long
) : Parcelable

