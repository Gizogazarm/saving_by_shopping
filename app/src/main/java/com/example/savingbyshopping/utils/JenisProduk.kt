package com.example.savingbyshopping.utils

enum class JenisProduk(val value: String) {
    BAHAN_SEMBAKO("Bahan Sembako"),
    PRODUK_SEGAR("Produk Segar"),
    MAKANAN_KEMASAN("Makanan Kemasan"),
    MINUMAN_RINGAN("Minuman Ringan"),
    ALAT_KEBERSIHAN("Alat Kebersihan"),
    PERAWATAN_DIRI("Perawatan Diri"),
    PERALATAN_RUMAH("Peralatan Rumah"),
    KEBUTUHAN_BAYI("Kebutuhan Bayi"),
    PRODUK_KESEHATAN("Produk Kesehatan"),
    ALAT_ELEKTRONIK("Alat Elektronik"),
    ALAT_TULIS("Alat Tulis"),
    PRODUK_LAINNYA("Produk Lainnya");

    companion object {
        fun fromValue(value: String): JenisProduk {
            return values().find { it.value == value } ?: PRODUK_LAINNYA
        }
    }
}
