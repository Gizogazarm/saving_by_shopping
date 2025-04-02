package com.example.savingbyshopping.utils

import com.example.savingbyshopping.utils.JenisProduk.PRODUK_LAINNYA
import junit.framework.TestCase.assertEquals
import org.junit.Test


class JenisProdukTest {

    @Test
    fun convertJenisProduk() {
        val expected = JenisProduk.BAHAN_SEMBAKO
        val actual = JenisProduk.fromValue("Bahan Sembako")
        assertEquals(expected, actual)
    }

    @Test
    fun tesListUntukJenisProduk() {
        val expected = "Wrong Answer"
        val list = JenisProduk.entries.map { it.value } //membuat list dari enum class
        var actual = list[1] // nilainya adalah "Produk Segar"
        if (actual != "Bahan Sembako") {
            actual = "Wrong Answer"
        }
        assertEquals(expected, actual)
    }

    @Test
    fun tesProdukYangTidakTerdaftar() { // ketika enum class tidak ada yang sesuai jika menggunakan edit text maka akan menampilkan Produk Lainnya
        val expected = PRODUK_LAINNYA
        val actual: JenisProduk = JenisProduk.fromValue("Makanan")
        assertEquals(expected, actual)
    }
}