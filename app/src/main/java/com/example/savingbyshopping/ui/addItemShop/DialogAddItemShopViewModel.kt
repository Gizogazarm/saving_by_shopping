package com.example.savingbyshopping.ui.addItemShop

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.savingbyshopping.utils.Condition

class DialogAddItemShopViewModel : ViewModel() {

    private val _quantity = MutableLiveData(0)
    val quantity: LiveData<Int> = _quantity

    private val _afterPriceManual = MutableLiveData<Long>()
    val afterPriceManual: LiveData<Long> = _afterPriceManual

    private val _afterPriceLocked = MutableLiveData<Long>(0)
    val afterPriceLocked: LiveData<Long> = _afterPriceLocked

    private val _condition = MutableLiveData(Condition.NONE)
    val condition: LiveData<Condition> = _condition

    private val _isAfterPriceLocked = MutableLiveData(false)
    val isAfterPriceLocked: LiveData<Boolean> get() = _isAfterPriceLocked

    private val _isBuyFreeEditable = MutableLiveData(true)
    val isBuyFreeEditable: LiveData<Boolean> get() = _isBuyFreeEditable

    fun incrementQuantity() {
        _quantity.value = _quantity.value?.plus(1)
    }

    fun decrementQuantity() {
        if (_quantity.value!! > 0) {
            _quantity.value = _quantity.value?.minus(1)
        } else {
            _quantity.value = 0
        }
    }

    fun setCondition(condition: Condition) {
        when (condition) {
            Condition.NONE -> {
                _isAfterPriceLocked.value = false
                _isBuyFreeEditable.value = true
                _condition.value = Condition.NONE
            }
            Condition.BUY_ITEM_FREE_ITEM -> {
                _isAfterPriceLocked.value = true
                _isBuyFreeEditable.value = false
                _condition.value = Condition.BUY_ITEM_FREE_ITEM
            }
            Condition.NO_DISCOUNT -> {
                _isAfterPriceLocked.value = true
                _isBuyFreeEditable.value = true
                _condition.value = Condition.NO_DISCOUNT
            }
        }
    }

    fun settingAfterPrice(condition: Condition, price: Long) {
        when(condition) {
            Condition.NONE -> {
                _afterPriceLocked.value = _afterPriceManual.value

            }
            Condition.BUY_ITEM_FREE_ITEM -> {
                _afterPriceLocked.value = 0
            }
            Condition.NO_DISCOUNT -> {
                _afterPriceLocked.value = 0
            }
        }
    }

    fun setAfterPriceManually(afterPrice: Long) {
        _afterPriceManual.value = afterPrice
    }

}