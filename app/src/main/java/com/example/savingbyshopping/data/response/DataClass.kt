package com.example.savingbyshopping.data.response


import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.savingbyshopping.utils.Condition
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
    val idItem: Long = 0L,
    val idShoppingList: Long,
    var namaItem: String,
    val jenisProduk: String,
    val hargaAsli: Long,
    var hargaDiskon: Long,
    val quantity: Int,
    val totalHarga: Long,
    val saveDiskon: Long,
    var buyItemFree: Int = 0,
    var freeItem: Int = 0,
    var condition: Condition = Condition.NONE,
    var isDiskon: Boolean = false
) : Parcelable

@Parcelize
@Entity(tableName = "shopping_list")
data class ShoppingList(

    @PrimaryKey(autoGenerate = true)
    val idShoppingList: Long = 0L,
    val tanggalTransaksi: String,
    var namaToko: String,
    var totalBelanja: Long = 0L,
    var totalDiskon: Long = 0L,
    var email: String? = "default"

) : Parcelable

@Parcelize
data class ShoppingListWithItemShop(
    @Embedded val shoppingList: ShoppingList,
    @Relation(
        parentColumn = "idShoppingList",
        entityColumn = "idShoppingList"
    ) val itemShop: List<ItemShop>
) : Parcelable