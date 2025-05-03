package com.example.savingbyshopping.ui.addShoppingList

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.savingbyshopping.data.repository.ShoppingListRepository
import com.example.savingbyshopping.fakedao.FakeItemShopDao
import com.example.savingbyshopping.fakedao.FakeShoppingListDao
import com.example.savingbyshopping.utils.DataDummy
import com.example.savingbyshopping.utils.MainDispatcherRule
import com.example.savingbyshopping.utils.getOrAwaitValue
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertNotNull


@ExperimentalCoroutinesApi
class AddShoppingListViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule() //untuk livedata secara live

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var fakeShoppingListRepository: ShoppingListRepository
    private lateinit var fakeItemShopDao: FakeItemShopDao
    private lateinit var fakeShoppingListDao: FakeShoppingListDao
    private lateinit var viewModel: AddShoppingListViewModel
    private val dummyShoppingList = DataDummy.generateDummyAllShoppingList()
    private val dummyItemShop = DataDummy.generateDummyItemShop()
    private val dummyShoppingListFilter = DataDummy.generateDummyShoppingListFilter()
    private val dummyShoppingListWithItemShop =
        DataDummy.generateDummyShoppingListWithItemShop(dummyItemShop,37)

    @Before
    fun setup() {
        fakeItemShopDao = FakeItemShopDao()
        fakeShoppingListDao = FakeShoppingListDao()
        fakeShoppingListRepository = ShoppingListRepository(fakeShoppingListDao, fakeItemShopDao)
        viewModel = AddShoppingListViewModel(fakeShoppingListRepository)
    }

    @Test
    fun `test berhasil input Shopping List`() = runTest {
        val expected = dummyShoppingList[0]
        viewModel.inputShoppingList(expected)
        val actual = viewModel.ambilSemuaShoppingList().getOrAwaitValue()
        assertEquals(expected, actual[0])
    }

    @Test
    fun `ambil shopping list by id`() {
        fakeShoppingListDao.copyDariDummyList(dummyShoppingList)
        val actual = viewModel.ambilShoppingListDenganId(1).getOrAwaitValue()
        assertEquals(dummyShoppingList[0], actual) //mencari sama dengan data yang dicari
        assertNotNull(actual)
    }

    @Test
    fun `ambil shopping list dengan item shop by id`() {
        fakeShoppingListDao.copyDataUntukShoppingListWithItemShop(dummyShoppingListFilter, dummyItemShop)
        val actual = viewModel.ambilShoppingListDenganItemShopById(37).getOrAwaitValue()
        assertEquals(dummyShoppingListWithItemShop, actual) // Test One to Many pada viewmodel
    }

    @Test
    fun `test berhasil perbarui Shopping List`() = runTest {
        fakeShoppingListDao.copyDariDummyList(dummyShoppingList)
        val data = viewModel.ambilShoppingListDenganId(37).getOrAwaitValue()
        data.totalBelanja = "Rp1.000.000"
        data.totalDiskon = "Rp100.000"
        viewModel.perbaruiShoppingList(data)
        val actual = viewModel.ambilShoppingListDenganId(37).getOrAwaitValue()
        assertEquals(data, actual)
    }

    @Test
    fun `test berhasil hapus Shopping List`() = runTest {
        fakeShoppingListDao.copyDariDummyList(dummyShoppingList)
        val data = viewModel.ambilShoppingListDenganId(37).getOrAwaitValue()
        viewModel.hapusShoppingList(data)
        val actual = viewModel.ambilSemuaShoppingList().getOrAwaitValue()
        assertEquals(dummyShoppingList.size-1, actual.size)
    }

}