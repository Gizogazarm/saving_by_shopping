package com.example.savingbyshopping.data.response


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(
    foreignKeys = [
        ForeignKey(
            entity = ShoppingList::class,
            parentColumns = ["idShoppingList"],
            childColumns = ["idShoppingList"]
        )
    ]
)
data class ItemShop(
    @PrimaryKey(autoGenerate = true)
    val idItem: Int,
    val idShoppingList: Int,
    val namaItem: String,
    val jenisProduk: String,
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
    val totalBelanja: String,
    val totalDiskon: String,

    )
