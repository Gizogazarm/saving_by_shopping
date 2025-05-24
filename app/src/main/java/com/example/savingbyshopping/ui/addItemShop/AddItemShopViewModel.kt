package com.example.savingbyshopping.ui.addItemShop

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.savingbyshopping.data.repository.ShoppingListRepository
import com.example.savingbyshopping.data.response.ItemShop
import kotlinx.coroutines.launch

class AddItemShopViewModel(private val shoppingListRepository: ShoppingListRepository) : ViewModel() {

    fun ambilSemuaItemShop() = shoppingListRepository.ambilSemuaItemShop()
    fun inputItemShop(itemShop: ItemShop) = viewModelScope.launch {
        shoppingListRepository.inputItemShop(itemShop)
    }

    fun hapusItemShop(itemShop: ItemShop) = viewModelScope.launch {
        shoppingListRepository.hapusItemShop(itemShop)
    }

    fun perbaruiItemShop(itemShop: ItemShop) = viewModelScope.launch {
        shoppingListRepository.perbaruiItemShop(itemShop)
    }

    fun ambilItemShopById(id: Long) = shoppingListRepository.ambilItemShopById(id)




}