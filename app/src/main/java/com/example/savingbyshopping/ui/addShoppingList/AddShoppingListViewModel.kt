package com.example.savingbyshopping.ui.addShoppingList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.savingbyshopping.data.repository.ShoppingListRepository
import com.example.savingbyshopping.data.response.ShoppingList
import com.example.savingbyshopping.utils.toRupiah
import kotlinx.coroutines.launch

class AddShoppingListViewModel(private val shoppingListRepository: ShoppingListRepository) : ViewModel() {

    private val _insertResult = MutableLiveData<Long>()
    val insertResult : LiveData<Long> = _insertResult

    fun ambilSemuaShoppingList() = shoppingListRepository.ambilSemuaShoppingList()
    fun ambilShoppingListDenganId(id: Long) = shoppingListRepository.ambilShoppingListDenganId(id)
    fun ambilShoppingListDenganItemShopById(id: Long) = shoppingListRepository.ambilShoppingListDenganItemShopById(id)

    fun inputShoppingList(shoppingList: ShoppingList) = viewModelScope.launch {
        val id = shoppingListRepository.inputShoppingList(shoppingList)
        _insertResult.postValue(id)

    }

    fun perbaruiShoppingList(shoppingList: ShoppingList) = viewModelScope.launch {
        shoppingListRepository.perbaruiShoppingList(shoppingList)
    }

    fun hapusShoppingList(shoppingList: ShoppingList) = viewModelScope.launch {
        shoppingListRepository.hapusShoppingList(shoppingList)

    }

    fun calculateAllSavingDiscountUser(list: List<ShoppingList>): String {
        var total = 0L

        if (list.isEmpty()) {
            total = 0L
        } else {
            for (item in list) {
                total += item.totalDiskon
            }
        }

        return total.toRupiah()
    }


}