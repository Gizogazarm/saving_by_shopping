package com.example.savingbyshopping.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.savingbyshopping.data.dao.ItemShopDao
import com.example.savingbyshopping.data.dao.ShoppingListDao
import com.example.savingbyshopping.data.response.ItemShop
import com.example.savingbyshopping.data.response.ShoppingList


@Database(entities = [ShoppingList::class, ItemShop::class], version = 1, exportSchema = false)
abstract class ShoppingDatabase : RoomDatabase() {

    abstract fun shoppingListDao(): ShoppingListDao
    abstract fun itemShopDao(): ItemShopDao

    companion object {

        private var instance: ShoppingDatabase? = null

        fun getInstance(context: Context): ShoppingDatabase = instance ?: synchronized(this) {
            instance ?: Room.databaseBuilder(
                context.applicationContext,
                ShoppingDatabase::class.java,
                "shopping_database"
            ).build().also { instance = it }
        }
    }
}
