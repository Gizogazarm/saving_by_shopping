package com.example.savingbyshopping.utils

import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test


class ItemHargaTest {

    private lateinit var itemHarga: ItemHarga
    private val dummyItemShop = DataDummy.generateDataDummySorted()

    @Before
    fun setUp() {
        itemHarga = ItemHarga(dummyItemShop)
    }

    @Test
    fun `hitung total harga belanja`() {
        val expected = 32800L
        val actual = itemHarga.hitungTotalHarga()
        assertEquals(expected, actual)
    }

    @Test
    fun `hitung total diskon belanja`() {
        val expected = 8200L
        val actual = itemHarga.hitungTotalDiskon()
        assertEquals(expected, actual)
    }

    @Test
    fun`set harga diskon`(){
        val discount =  60.toDecimalPercetage()
        itemHarga.setHargaDiscount(discount)
        val expected = 4000L
        val actual = dummyItemShop[0].hargaDiskon // Harga sebelum diskon adalah 10000
        assertEquals(expected, actual)
    }


}