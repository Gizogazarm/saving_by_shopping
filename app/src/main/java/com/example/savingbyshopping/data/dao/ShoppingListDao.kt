package com.example.savingbyshopping.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.savingbyshopping.data.response.ShoppingList
import com.example.savingbyshopping.data.response.ShoppingListWithItemShop

@Dao
interface ShoppingListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inputShoppingList(shoppingList: ShoppingList)

    @Update
    suspend fun perbaruiShoppingList(shoppingList: ShoppingList)

    @Query("SELECT * FROM shopping_list")
    fun ambilSemuaShoppingList(): LiveData<List<ShoppingList>>

    @Delete
    suspend fun hapusShoppingList(shoppingList: ShoppingList)

    @Query("SELECT * FROM shopping_list WHERE idShoppingList = :id")
    fun ambilShoppingListDenganId(id: Int): LiveData<ShoppingList>

    @Transaction
    @Query("SELECT * FROM shopping_list WHERE idShoppingList = :id")
    fun ambilShoppingListDenganItemShopById(id: Int): LiveData<ShoppingListWithItemShop>

}

