package com.example.savingbyshopping.data.injection

import android.content.Context
import com.example.savingbyshopping.data.database.ShoppingDatabase
import com.example.savingbyshopping.data.repository.ShoppingListRepository

object Injection {

    fun provideRepository(context: Context): ShoppingListRepository {
        val database = ShoppingDatabase.getInstance(context)
        val shoppingListDao = database.shoppingListDao()
        val itemShopDao = database.itemShopDao()
        return ShoppingListRepository(shoppingListDao, itemShopDao)
    }

}