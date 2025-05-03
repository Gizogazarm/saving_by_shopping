package com.example.savingbyshopping.utils

import com.example.savingbyshopping.data.response.ItemShop
import com.example.savingbyshopping.data.response.ShoppingList
import com.example.savingbyshopping.data.response.ShoppingListWithItemShop


object DataDummy {

    fun generateDummyItemShop(): List<ItemShop> {
        val itemShopList = ArrayList<ItemShop>()
        for (i in 1..50) {
            val itemShop = ItemShop(
                idItem = i,
                idShoppingList = i,
                namaItem = "Sikat Gigi",
                jenisProduk = JenisProduk.ALAT_KEBERSIHAN.value,
                hargaAsli = 10000,
                hargaDiskon = 8000,
                quantity = i,
                totalHarga = menghitungHargaBelanja(8000, quantity = i),
                saveDiskon = menghitungSavingDiskon(10000, 8000, 2)
            )
            itemShopList.add(itemShop)
        }
        return itemShopList
    }

    fun generateDummyAllShoppingList(): List<ShoppingList> {
        val shoppingListList = ArrayList<ShoppingList>()
        for (i in 1..50) {
            val shoppingList = ShoppingList(
                idShoppingList = i,
                tanggalTransaksi = "13/04/2025",
                namaToko = "Toko $i",
            )
            shoppingListList.add(shoppingList)
        }
        return shoppingListList
    }

    fun generateDummyShoppingListFilter(): List<ShoppingList> {
        val data = listOf(
            ShoppingList(
                idShoppingList = 37,
                tanggalTransaksi = "13/04/2025",
                namaToko = "Toko 37",
            ),
            ShoppingList(
                idShoppingList = 49,
                tanggalTransaksi = "15/07/2025",
                namaToko = "Toko 49",
            )
        )
        return data
    }

    fun generateDummyShoppingListWithItemShop(list: List<ItemShop>, i : Int): ShoppingListWithItemShop {
        val data = ShoppingListWithItemShop(
            shoppingList = ShoppingList(
                idShoppingList = i,
                tanggalTransaksi = "13/04/2025",
                namaToko = "Toko $i",
            ), itemShop = list.filter { it.idShoppingList == i }
        )
        return data
    }

}