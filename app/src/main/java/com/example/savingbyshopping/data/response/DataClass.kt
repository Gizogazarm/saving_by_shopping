package com.example.savingbyshopping.data.response


import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(
    foreignKeys = [
        ForeignKey(
            entity = ShoppingList::class,
            parentColumns = ["idShoppingList"],
            childColumns = ["idShoppingList"],
            onDelete = ForeignKey.CASCADE
        )
    ], tableName = "itemshop"
)
data class ItemShop(
    @PrimaryKey(autoGenerate = true)
    val idItem: Int,
    val idShoppingList: Int,
    var namaItem: String,
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
    var namaToko: String,
    val totalBelanja: String? = null,
    val totalDiskon: String? = null,

    )

data class ShoppingListWithItemShop(
    @Embedded val shoppingList: ShoppingList,
    @Relation(
        parentColumn = "idShoppingList",
        entityColumn = "idShoppingList"
    ) val itemShop: List<ItemShop>
)