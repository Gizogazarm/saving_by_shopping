package com.example.savingbyshopping.utils

import java.text.NumberFormat
import java.util.Locale

fun Long.toRupiah(): String {
    val localeID = Locale("in", "ID")
    val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
    return formatRupiah.format(this).replace(",00", "")
}

fun String.fromRupiah(): Long {
    return this.replace("[Rp.\\s]".toRegex(), "")
        .replace(",", ".")
        .substringBefore(".")
        .toLongOrNull() ?: 0L
}

fun menghitungHargaBelanja(hargaDiskon: Long, quantity: Int): Long {
    return hargaDiskon * quantity
}

fun menghitungSavingDiskon(hargaAsli: Long, hargaDiskon: Long, quantity: Int): Long {
    return (hargaAsli - hargaDiskon) * quantity
}



