package com.example.savingbyshopping.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.savingbyshopping.data.injection.Injection
import com.example.savingbyshopping.data.repository.ShoppingListRepository
import com.example.savingbyshopping.ui.addItemShop.AddItemShopViewModel
import com.example.savingbyshopping.ui.addShoppingList.AddShoppingListViewModel

class ViewModelFactory private constructor(private val shoppingListRepository: ShoppingListRepository) :
    ViewModelProvider.Factory {

    companion object {

        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory = instance ?: synchronized(this) {
            instance ?: ViewModelFactory(Injection.provideRepository(context))
        }.also { instance = it }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddShoppingListViewModel::class.java)) {
            return AddShoppingListViewModel(shoppingListRepository) as T
        }
        if (modelClass.isAssignableFrom(AddItemShopViewModel::class.java)) {
            return AddItemShopViewModel(shoppingListRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

}

