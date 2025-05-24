package com.example.savingbyshopping.utils

import junit.framework.TestCase.assertEquals
import org.junit.Test


class CurrencyKtTest {

    private val input1: Long = 1000000
    private val input2: Long = 500
    private val input3: Long = 10000
    private val input4: Long = 0

    private val input1Str: String = "Rp1.000.000"
    private val input2Str: String = "Rp500"
    private val input3Str: String = "Rp10.000"
    private val input4Str: String = "Rp1"

    @Test
    fun `Mengubah Long ke String Rupiah`() {
        val expected = "Rp 1.000.000"
        val actual = input1.toRupiah()
        assertEquals(expected, actual)

        val expected2 = "Rp 500"
        val actual2 = input2.toRupiah()
        assertEquals(expected2, actual2)

        val expected3 = "Rp 10.000"
        val actual3 = input3.toRupiah()
        assertEquals(expected3, actual3)

        val expected4 = "Rp 0"
        val actual4 = input4.toRupiah()
        assertEquals(expected4, actual4)
    }

    @Test
    fun `Mengubah String Rupiah ke Long`() {
        val expected: Long = 1000000
        val actual = input1Str.fromRupiah()
        assertEquals(expected, actual)

        val expected2: Long = 500
        val actual2 = input2Str.fromRupiah()
        assertEquals(expected2, actual2)

        val expected3: Long = 10000
        val actual3 = input3Str.fromRupiah()
        assertEquals(expected3, actual3)

        val expected4: Long = 1
        val actual4 = input4Str.fromRupiah()
        assertEquals(expected4, actual4)
    }

    @Test
    fun `Menghitung Harga Belanja dari Harga Diskon dan Quantity`() {
        val expected: Long = 3000000
        val quantity = 3
        val actual = menghitungHargaBelanja(input1, quantity)
        assertEquals(expected, actual)
    }

    @Test
    fun `Menghitung Saving Diskon dari Harga Asli, Harga Diskon, dan Quantity`() {
        val expected: Long = 50000
        val quantity = 1
        val hargaAsli: Long = 1000000
        val hargaDiskon: Long = 950000
        val actual = menghitungSavingDiskon(hargaAsli, hargaDiskon, quantity)
        assertEquals(expected, actual)
    }

    @Test
    fun `Validasi Discount`() {
        val isChecked = true
        val hargaAsli: Long = 1000000
        val expected: Long = 0
        val actual = isNoDiscount(isChecked, hargaAsli)
        assertEquals(expected, actual)
    }

    @Test
    fun`Convert Percentage ke Decimal`(){
        val input = 60
        val expected = 0.6
        val actual = input.toDecimalPercetage()
        assertEquals(expected, actual)
    }

    @Test
    fun `Set Price Discount`(){
        val discount =  60.toDecimalPercetage()
        val expected = 400000L
        val actual = setPriceDiscount(discount, input1)
        assertEquals(expected, actual)
    }

}

