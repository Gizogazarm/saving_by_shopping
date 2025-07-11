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

    fun plusItemQuantity(itemShop: ItemShop): ItemShop {
        return when (itemShop.condition) {
            Condition.NONE -> {
                val newQuantity = itemShop.quantity + 1
                val newTotalHarga = hitungHarga(itemShop.hargaDiskon, newQuantity)
                val newSaveDiskon =
                    menghitungSavingDiskon(itemShop.hargaAsli, itemShop.hargaDiskon, newQuantity)
                itemShop.copy(
                    quantity = newQuantity,
                    totalHarga = newTotalHarga,
                    saveDiskon = newSaveDiskon
                )
            }

            Condition.BUY_ITEM_FREE_ITEM -> {
                itemShop // Perlu di Maintenance Ulang
            }

            Condition.NO_DISCOUNT -> {
                val newQuantity = itemShop.quantity + 1
                val newTotalHarga = hitungHarga(itemShop.hargaAsli, newQuantity)
                itemShop.copy(
                    quantity = newQuantity,
                    totalHarga = newTotalHarga
                )
            }
        }
    }

    fun minItemQuantity(item: ItemShop): ItemShop {
        return when (item.condition) {
            Condition.NONE -> {
                if (item.quantity > 1) {
                    val newQuantity = item.quantity - 1
                    val newTotalHarga = hitungHarga(item.hargaDiskon, newQuantity)
                    val newSaveDiskon =
                        menghitungSavingDiskon(item.hargaAsli, item.hargaDiskon, newQuantity)
                    item.copy(
                        quantity = newQuantity,
                        totalHarga = newTotalHarga,
                        saveDiskon = newSaveDiskon
                    )
                } else {
                    val newQuantity = 1
                    val newTotalHarga = hitungHarga(item.hargaDiskon, newQuantity)
                    val newSaveDiskon =
                        menghitungSavingDiskon(item.hargaAsli, item.hargaDiskon, newQuantity)
                    item.copy(
                        quantity = newQuantity,
                        totalHarga = newTotalHarga,
                        saveDiskon = newSaveDiskon
                    )
                }
            }

            Condition.BUY_ITEM_FREE_ITEM -> {
                item // perlu di maintenance ulang
            }

            Condition.NO_DISCOUNT -> {
                if (item.quantity > 1) {
                    val newQuantity = item.quantity - 1
                    val newTotalHarga = hitungHarga(item.hargaAsli, newQuantity)
                    item.copy(quantity = newQuantity, totalHarga = newTotalHarga)
                } else {
                    val newQuantity = 1
                    val newTotalHarga = hitungHarga(item.hargaAsli, newQuantity)
                    item.copy(quantity = newQuantity, totalHarga = newTotalHarga)
                }
            }

        }
    }


}