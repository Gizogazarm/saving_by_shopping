package com.example.savingbyshopping.ui.addItemShop

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.savingbyshopping.data.repository.ShoppingListRepository
import com.example.savingbyshopping.data.response.ItemShop
import com.example.savingbyshopping.utils.DataDummy
import com.example.savingbyshopping.utils.MainDispatcherRule
import com.example.savingbyshopping.utils.getOrAwaitValue
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertNull

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class AddItemShopViewModelTest {


    @Mock
    private lateinit var shoppingListRepository: ShoppingListRepository
    private lateinit var addItemShopViewModel: AddItemShopViewModel
    private val dummyItemShop = DataDummy.generateDummyItemShop()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setUp() {
        addItemShopViewModel = AddItemShopViewModel(shoppingListRepository)
    }

    //testing alur view Model
    @Test
    fun `test berhasil ambilSemuaItemShop`() {
        val expected = MutableLiveData<List<ItemShop>>()
        expected.value = dummyItemShop
        `when`(shoppingListRepository.ambilSemuaItemShop()).thenReturn(expected)
        val actual = addItemShopViewModel.ambilSemuaItemShop().getOrAwaitValue()
        verify(shoppingListRepository).ambilSemuaItemShop()
        assertEquals(expected.value, actual)
    }

    @Test
    fun `Tes jika data adalah null ketika ambilSemuaItemShop`() {
        val expected = MutableLiveData<List<ItemShop>>()
        expected.value = null
        `when`(shoppingListRepository.ambilSemuaItemShop()).thenReturn(expected)
        val actual = addItemShopViewModel.ambilSemuaItemShop().getOrAwaitValue()
        verify(shoppingListRepository).ambilSemuaItemShop()
        assertNull(actual)
    }

    @Test
    fun `test berhasil inputItemShop`() = runTest {
        val dummyItemShop1 = DataDummy.generateDummyItemShop()[0]
        addItemShopViewModel.inputItemShop(dummyItemShop1)
        verify(shoppingListRepository).inputItemShop(dummyItemShop1)
    }


}