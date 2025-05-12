package com.example.savingbyshopping.utils

import com.example.savingbyshopping.data.response.ItemShop


class ItemHarga (private var list: List<ItemShop>) {

    fun hitungTotalHarga(): Long {
        var totalHarga = 0L
        for (item in list) {
            totalHarga += item.totalHarga
        }
        return totalHarga
    }

    fun hitungTotalDiskon(): Long {
        var totalDiskon = 0L
        for (item in list) {
            totalDiskon += item.saveDiskon
        }
        return totalDiskon
    }

    fun setHargaDiscount(discount: Double) { //Setting discount untuk semua harga diskon
        for (item in list) {
            item.hargaDiskon = (item.hargaAsli * (1 - discount)).toLong()
        }
    }


}