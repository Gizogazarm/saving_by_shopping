package com.example.savingbyshopping.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.savingbyshopping.fakedao.FakeItemShopDao
import com.example.savingbyshopping.fakedao.FakeShoppingListDao
import com.example.savingbyshopping.utils.DataDummy
import com.example.savingbyshopping.utils.MainDispatcherRule
import com.example.savingbyshopping.utils.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull


@ExperimentalCoroutinesApi
class ShoppingListRepositoryTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var shoppingListDao: FakeShoppingListDao
    private lateinit var itemShopDao: FakeItemShopDao
    private lateinit var shoppingListRepository: ShoppingListRepository
    private val dummyShoppingList = DataDummy.generateDummyAllShoppingList()
    private val dummyItemShop = DataDummy.generateDummyItemShop()
    private val dummyShoppingListWithItemShop =
        DataDummy.generateDummyShoppingListWithItemShop(dummyItemShop)

    @Before
    fun setup() {
        shoppingListDao = FakeShoppingListDao()
        itemShopDao = FakeItemShopDao()
        shoppingListRepository = ShoppingListRepository(shoppingListDao, itemShopDao)
    }

    @Test
    fun `input item shop`() = runTest {
        val expected = dummyItemShop[0]
        shoppingListRepository.inputItemShop(expected)
        val actual = shoppingListRepository.ambilSemuaItemShop().getOrAwaitValue()
        assert(actual.contains(expected))
    }

    @Test
    fun `hapus item shop`() = runTest {
        itemShopDao.copyDariDummyList(dummyItemShop)
        val expected = dummyItemShop[0] // menghapus data pertama dari dummyItemShop
        shoppingListRepository.hapusItemShop(expected)
        val actual = shoppingListRepository.ambilSemuaItemShop().getOrAwaitValue()
        assert(!actual.contains(expected))
        // maksudnya bernilai true karena data expected berhasil di
        // hapus tidak ada di dalam actual
    }

    @Test
    fun `perbarui item shop`() = runTest {
        itemShopDao.copyDariDummyList(dummyItemShop)
        val expected = dummyItemShop[0]
        expected.namaItem = "Beras"
        shoppingListRepository.perbaruiItemShop(expected)
        val actual = shoppingListRepository.ambilSemuaItemShop().getOrAwaitValue()
        assert(actual.contains(expected))
    }

    @Test
    fun `input shopping list`() = runTest {
        val expected = dummyShoppingList[0]
        shoppingListRepository.inputShoppingList(expected)
        val actual = shoppingListRepository.ambilSemuaShoppingList().getOrAwaitValue()
        assert(actual.contains(expected))
        assertNotNull(actual)
        assertEquals(dummyShoppingList[0], actual[0])
    }

    @Test
    fun `perbarui shopping list`() = runTest {
        shoppingListDao.copyDariDummyList(dummyShoppingList)
        val expected = dummyShoppingList[0]
        expected.namaToko = "Toko Baru"
        shoppingListRepository.perbaruiShoppingList(expected)
        val actual = shoppingListRepository.ambilSemuaShoppingList().getOrAwaitValue()
        assert(actual.contains(expected))
    }

    @Test
    fun `hapus shopping list`() = runTest {
        shoppingListDao.copyDariDummyList(dummyShoppingList)
        val expected = dummyShoppingList[0]
        shoppingListRepository.hapusShoppingList(expected)
        val actual = shoppingListRepository.ambilSemuaShoppingList().getOrAwaitValue()
        assert(!actual.contains(expected))

    }

    @Test
    fun `ambil semua shopping list id`() {
        shoppingListDao.copyDariDummyList(dummyShoppingList)
        val actual = shoppingListRepository.ambilShoppingListDenganId(1).getOrAwaitValue()
        assertEquals(
            dummyShoppingList[0], actual
        ) // Jika data tidak ditemukan maka null jadi perlu dibuat penanganannya
    }

    @Test
    fun `tes (one of many ) ambil semua shopping list dengan id shopping list`() {
        shoppingListDao.copyDataUntukShoppingListWithItemShop(dummyShoppingList, dummyItemShop)
        val actual = shoppingListDao.ambilShoppingListDenganItemShopById(1).getOrAwaitValue()
        assertEquals(dummyShoppingListWithItemShop, actual)
        // Berhasil mendapatkan data yang sama shoplistwithItembedasarkanID
        // jika tidak ada data maka akan null (perlu di buat penanganannya)
        // jika ada data yang terkait dengan nomer id maka akan tampil data itemshop
    }


}