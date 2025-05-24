package com.example.savingbyshopping.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.savingbyshopping.data.response.ItemShop

@Dao
interface ItemShopDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inputItemShop(itemShop: ItemShop)

    @Delete
    suspend fun hapusItemShop(itemShop: ItemShop)

    @Update
    suspend fun perbaruiItemShop(itemShop: ItemShop)

    @Query("SELECT * FROM itemshop")
    fun ambilSemuaItemShop(): LiveData<List<ItemShop>>

    @Query("SELECT * FROM itemshop WHERE idItem = :id")
    fun ambilItembyId(id : Long) : LiveData<ItemShop>




}