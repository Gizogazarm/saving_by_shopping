package com.example.savingbyshopping.ui.addItemShop

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.savingbyshopping.data.repository.ShoppingListRepository
import com.example.savingbyshopping.data.response.ItemShop
import com.example.savingbyshopping.utils.Condition
import com.example.savingbyshopping.utils.hitungHarga
import com.example.savingbyshopping.utils.menghitungSavingDiskon
import kotlinx.coroutines.launch

class AddItemShopViewModel(private val shoppingListRepository: ShoppingListRepository) :
    ViewModel() {

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

    fun setQuantity(itemShop: ItemShop, quantity: Int): ItemShop {
        return when (itemShop.condition) {
            Condition.NONE -> {
                calculateItemPrice(itemShop, quantity)
            }

            Condition.BUY_ITEM_FREE_ITEM -> {
                calculateBuyItemFreeItem(quantity, itemShop)
            }

            Condition.NO_DISCOUNT -> {
                calculateItemPrice(itemShop, quantity)
            }
        }
    }

    fun plusItemQuantity(itemShop: ItemShop): ItemShop {
        return when (itemShop.condition) {
            Condition.NONE -> {
                val newQuantity = itemShop.quantity + 1
                calculateItemPrice(itemShop, newQuantity)
            }

            Condition.BUY_ITEM_FREE_ITEM -> {
                val newQuantity = itemShop.quantity + 1
                calculateBuyItemFreeItem(newQuantity, itemShop)
            }

            Condition.NO_DISCOUNT -> {
                val newQuantity = itemShop.quantity + 1
                calculateItemPrice(itemShop, newQuantity)
            }
        }
    }

    fun minItemQuantity(itemShop: ItemShop): ItemShop {
        return when (itemShop.condition) {
            Condition.NONE -> {
                if (itemShop.quantity > 1) {
                    val newQuantity = itemShop.quantity - 1
                    calculateItemPrice(itemShop, newQuantity)
                } else {
                    val newQuantity = 1
                    calculateItemPrice(itemShop, newQuantity)
                }
            }

            Condition.BUY_ITEM_FREE_ITEM -> {
                if (itemShop.quantity > 1) {
                    val newQuantity = itemShop.quantity - 1
                    calculateBuyItemFreeItem(newQuantity, itemShop)
                } else {
                    val newQuantity = 1
                    calculateBuyItemFreeItem(newQuantity, itemShop)
                }
            }

            Condition.NO_DISCOUNT -> {
                if (itemShop.quantity > 1) {
                    val newQuantity = itemShop.quantity - 1
                    calculateItemPrice(itemShop, newQuantity)
                } else {
                    val newQuantity = 1
                    calculateItemPrice(itemShop, newQuantity)
                }
            }

        }
    }

    private fun calculateBuyItemFreeItem(batch: Int, itemShop: ItemShop): ItemShop {
        val newBuyItem = itemShop.buyItemFree * batch
        val newFreeItem = itemShop.freeItem * batch
        val newTotalHarga = hitungHarga(itemShop.hargaAsli, newBuyItem)
        val newSaveDiskon = hitungHarga(itemShop.hargaAsli, newFreeItem)
        return itemShop.copy(
            quantity = batch,
            totalHarga = newTotalHarga,
            saveDiskon = newSaveDiskon
        )
    }

    private fun calculateItemPrice(itemShop: ItemShop, newQuantity: Int): ItemShop {
        return if (itemShop.condition == Condition.NONE) {
            val newTotalHarga = hitungHarga(itemShop.hargaDiskon, newQuantity)
            val newSavingDiskon =
                menghitungSavingDiskon(itemShop.hargaAsli, itemShop.hargaDiskon, newQuantity)
            itemShop.copy(
                quantity = newQuantity,
                totalHarga = newTotalHarga,
                saveDiskon = newSavingDiskon
            )
        } else {
            val newTotalHarga = hitungHarga(itemShop.hargaAsli, newQuantity)
            itemShop.copy(
                quantity = newQuantity,
                totalHarga = newTotalHarga
            )
        }
    }

}