package com.example.savingbyshopping.ui.addShoppingList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.savingbyshopping.data.repository.ShoppingListRepository
import com.example.savingbyshopping.data.response.ShoppingList
import kotlinx.coroutines.launch

class AddShoppingListViewModel(private val shoppingListRepository: ShoppingListRepository) : ViewModel() {

    fun ambilSemuaShoppingList() = shoppingListRepository.ambilSemuaShoppingList()
    fun ambilShoppingListDenganId(id: Int) = shoppingListRepository.ambilShoppingListDenganId(id)
    fun ambilShoppingListDenganItemShopById(id: Int) = shoppingListRepository.ambilShoppingListDenganItemShopById(id)

    fun inputShoppingList(shoppingList: ShoppingList) = viewModelScope.launch {
        shoppingListRepository.inputShoppingList(shoppingList)
    }

    fun perbaruiShoppingList(shoppingList: ShoppingList) = viewModelScope.launch {
        shoppingListRepository.perbaruiShoppingList(shoppingList)
    }

    fun hapusShoppingList(shoppingList: ShoppingList) = viewModelScope.launch {
        shoppingListRepository.hapusShoppingList(shoppingList)

    }


}