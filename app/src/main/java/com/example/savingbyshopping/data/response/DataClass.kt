package com.example.savingbyshopping.data.response


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.savingbyshopping.utils.JenisProduk
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemShop(
    val namaItem: String,
    val jenisProduk: JenisProduk,
    val hargaAsli: Long,
    val hargaDiskon: Long,
    val quantity: Int,
    val totalHarga: Long,
    val saveDiskon: Long
) : Parcelable

@Entity(tableName = "shopping_list")
data class ShoppingList(

    @PrimaryKey(autoGenerate = true)
    val idShoppingList: Int,
    val tanggalTransaksi: String,
    val namaToko: String,
    val itemList: List<ItemShop>,
    val totalBelanja: String,
    val totalDiskon: String,

    )
