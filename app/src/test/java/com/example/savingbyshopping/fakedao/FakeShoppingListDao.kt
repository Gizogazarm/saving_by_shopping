package com.example.savingbyshopping.fakedao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.savingbyshopping.data.dao.ShoppingListDao
import com.example.savingbyshopping.data.response.ItemShop
import com.example.savingbyshopping.data.response.ShoppingList
import com.example.savingbyshopping.data.response.ShoppingListWithItemShop


class FakeShoppingListDao : ShoppingListDao {

    private var shoppingLists = mutableListOf<ShoppingList>()
    private var itemshops = listOf<ItemShop>()
    private val fakeItemShopDao = FakeItemShopDao()

    override suspend fun inputShoppingList(shoppingList: ShoppingList) {
        shoppingLists.add(shoppingList)
    }

    override suspend fun perbaruiShoppingList(shoppingList: ShoppingList) {
        shoppingLists.replaceAll { if (it.idShoppingList == shoppingList.idShoppingList) shoppingList else it }
    }

    override fun ambilSemuaShoppingList(): LiveData<List<ShoppingList>> {
        val liveData = MutableLiveData<List<ShoppingList>>()
        liveData.value = shoppingLists
        return liveData
    }

    override suspend fun hapusShoppingList(shoppingList: ShoppingList) {
        shoppingLists.remove(shoppingList)
        fakeItemShopDao.ambilItemShopDenganIdShoppingList(shoppingList.idShoppingList)
    }

    override fun ambilShoppingListDenganId(id: Int): LiveData<ShoppingList> {
        val liveData = MutableLiveData<ShoppingList>()
        liveData.value = shoppingLists.find { it.idShoppingList == id }
        return liveData
    }

    override fun ambilShoppingListDenganItemShopById(id: Int): LiveData<ShoppingListWithItemShop> {
        val shoppingListWithItemShop = MutableLiveData<ShoppingListWithItemShop>()
        val shoppingList = shoppingLists.find { it.idShoppingList == id }
        itemshops = itemshops.filter { it.idShoppingList == id }
        shoppingListWithItemShop.value = ShoppingListWithItemShop(shoppingList!!, itemshops)
        return shoppingListWithItemShop
    }

    fun copyDariDummyList(dummyList: List<ShoppingList>) {
        shoppingLists.clear()
        shoppingLists.addAll(dummyList)
    }

    fun copyDataUntukShoppingListWithItemShop(dummyShopList: List<ShoppingList>, dummyItemShop: List<ItemShop>) {
        itemshops = dummyItemShop
        shoppingLists = dummyShopList.toMutableList()
    }



}