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
                saveDiskon = menghitungSavingDiskon(10000, 8000, i)
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

    fun generateDummyShoppingListWithItemShop(
        list: List<ItemShop>,
        i: Int
    ): ShoppingListWithItemShop {
        val data = ShoppingListWithItemShop(
            shoppingList = ShoppingList(
                idShoppingList = i,
                tanggalTransaksi = "13/04/2025",
                namaToko = "Toko $i",
            ), itemShop = list.filter { it.idShoppingList == i }
        )
        return data
    }

    fun generateDataDummySorted(): List<ItemShop> {
        val data = listOf(
            ItemShop(
                idItem = 1,
                idShoppingList = 1,
                namaItem = "Sikat Gigi",
                jenisProduk = JenisProduk.ALAT_KEBERSIHAN.value,
                hargaAsli = 10000,
                hargaDiskon = 8000,
                quantity = 1,
                totalHarga = menghitungHargaBelanja(8000, quantity = 1),
                saveDiskon = menghitungSavingDiskon(10000, 8000, 1)
            ), ItemShop(
                idItem = 2,
                idShoppingList = 1,
                namaItem = "Sabun Mandi",
                jenisProduk = JenisProduk.ALAT_KEBERSIHAN.value,
                hargaAsli = 15000,
                hargaDiskon = 12000,
                quantity = 1,
                totalHarga = menghitungHargaBelanja(12000, quantity = 1),
                saveDiskon = menghitungSavingDiskon(15000, 12000, 1)
            ), ItemShop(
                idItem = 3,
                idShoppingList = 1,
                namaItem = "Pasta Gigi",
                jenisProduk = JenisProduk.ALAT_KEBERSIHAN.value,
                hargaAsli = 8000,
                hargaDiskon = 6400,
                quantity = 2,
                totalHarga = menghitungHargaBelanja(6400, quantity = 2),
                saveDiskon = menghitungSavingDiskon(8000, 6400, 2)
            )
        )
        return data
    }

}