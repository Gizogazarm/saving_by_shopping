package com.example.savingbyshopping.utils

import java.text.NumberFormat

fun Long.toRupiah(): String {
    val format = NumberFormat.getInstance(java.util.Locale("in", "ID"))
    return "Rp ${format.format(this)}"
}

fun String.fromRupiah(): Long {
    return this.replace("[Rp. \\s]".toRegex(), "").replace(",", ".").substringBefore(".")
        .toLongOrNull() ?: 0L
}

fun Int.toDecimalPercetage(): Double {
    return this.toDouble() / 100
}

fun menghitungHargaBelanja(hargaDiskon: Long, quantity: Int): Long { //hanya parameter
    return hargaDiskon * quantity
}

fun menghitungSavingDiskon(
    hargaAsli: Long,
    hargaDiskon: Long,
    quantity: Int
): Long { //hanya parameter
    return (hargaAsli - hargaDiskon) * quantity
}

fun isNoDiscount(isChecked: Boolean, hargaAsli: Long): Long {
    return if (isChecked) 0L else hargaAsli
}

fun setPriceDiscount(discount: Double, price: Long): Long { // Setting discount per item
    return (price * (1 - discount)).toLong()
}



