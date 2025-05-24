package com.example.savingbyshopping.fakedao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.savingbyshopping.data.dao.ItemShopDao
import com.example.savingbyshopping.data.response.ItemShop

class FakeItemShopDao : ItemShopDao {

    private val itemShops = mutableListOf<ItemShop>()

    override suspend fun inputItemShop(itemShop: ItemShop) {
        itemShops.add(itemShop)
    }

    override suspend fun hapusItemShop(itemShop: ItemShop) {
        itemShops.remove(itemShop)
    }

    override suspend fun perbaruiItemShop(itemShop: ItemShop) {
        itemShops.replaceAll { if (it.idItem == itemShop.idItem) itemShop else it }
    }

    override fun ambilSemuaItemShop(): LiveData<List<ItemShop>> {
        val liveData = MutableLiveData<List<ItemShop>>()
        liveData.value = itemShops
        return liveData
    }

    override fun ambilItembyId(id: Long): LiveData<ItemShop> {
        TODO("Not yet implemented")
    }

    fun ambilItemShopDenganIdShoppingList(id: Long): List<ItemShop> {
        return itemShops.filter { it.idShoppingList == id }
    }

    fun copyDariDummyList(dummyList: List<ItemShop>) {
        itemShops.clear()
        itemShops.addAll(dummyList)
    }
}
