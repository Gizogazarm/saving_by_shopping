package com.example.savingbyshopping.data.repository

import android.content.Context
import com.example.savingbyshopping.data.dao.ItemShopDao
import com.example.savingbyshopping.data.dao.ShoppingListDao
import com.example.savingbyshopping.data.database.ShoppingDatabase
import com.example.savingbyshopping.data.response.ItemShop
import com.example.savingbyshopping.data.response.ShoppingList

class ShoppingListRepository(private val shoppingListDao: ShoppingListDao,private val itemShopDao: ItemShopDao) {


    fun ambilSemuaShoppingList() = shoppingListDao.ambilSemuaShoppingList()
    fun ambilShoppingListDenganId(id: Int) = shoppingListDao.ambilShoppingListDenganId(id)
    fun ambilShoppingListDenganItemShopById(id: Int) = shoppingListDao.ambilShoppingListDenganItemShopById(id)
    suspend fun inputShoppingList(shoppingList: ShoppingList) = shoppingListDao.inputShoppingList(shoppingList)
    suspend fun perbaruiShoppingList(shoppingList: ShoppingList) = shoppingListDao.perbaruiShoppingList(shoppingList)
    suspend fun hapusShoppingList(shoppingList: ShoppingList) = shoppingListDao.hapusShoppingList(shoppingList)

    fun ambilSemuaItemShop() = itemShopDao.ambilSemuaItemShop()
    suspend fun inputItemShop(itemShop: ItemShop) = itemShopDao.inputItemShop(itemShop)
    suspend fun hapusItemShop(itemShop: ItemShop) = itemShopDao.hapusItemShop(itemShop)
    suspend fun perbaruiItemShop(itemShop: ItemShop) = itemShopDao.perbaruiItemShop(itemShop)


}