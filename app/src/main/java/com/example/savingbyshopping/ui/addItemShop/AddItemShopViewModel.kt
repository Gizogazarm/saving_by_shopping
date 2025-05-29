package com.example.savingbyshopping.ui.addItemShop

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.savingbyshopping.data.repository.ShoppingListRepository
import com.example.savingbyshopping.data.response.ItemShop
import kotlinx.coroutines.launch

class AddItemShopViewModel(private val shoppingListRepository: ShoppingListRepository) :
    ViewModel() {

    private val _quantity = MutableLiveData(0)
    val quantity: MutableLiveData<Int> = _quantity

    fun incrementQuantity() {
        _quantity.value = _quantity.value?.plus(1)
    }

    fun decrementQuantity() {
        if (_quantity.value!! > 0) {
            _quantity.value = _quantity.value?.minus(1)
        } else {
            _quantity.value = 0
        }
    }

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