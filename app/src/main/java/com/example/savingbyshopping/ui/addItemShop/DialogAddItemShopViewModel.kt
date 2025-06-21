package com.example.savingbyshopping.ui.addItemShop

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.savingbyshopping.utils.Condition
import com.example.savingbyshopping.utils.fromRupiah
import com.example.savingbyshopping.utils.hitungHarga
import com.example.savingbyshopping.utils.menghitungSavingDiskon
import com.example.savingbyshopping.utils.setPriceDiscount
import com.example.savingbyshopping.utils.toDecimalPercetage

class DialogAddItemShopViewModel : ViewModel() {

    private val _originalPrice = MutableLiveData<Long>(0)
    private val _afterPriceManual = MutableLiveData<Long>(0)

    private val _quantity = MutableLiveData(1)
    val quantity: LiveData<Int> = _quantity

    private val _quantityBuyFree = MutableLiveData(1)
    val quantityBuyFree: LiveData<Int> = _quantityBuyFree

    private val _quatityItemFree = MutableLiveData(1)
    val quantityItemFree: LiveData<Int> = _quatityItemFree

    private val _statusPercentage = MutableLiveData(false)
    val statusPercentage: LiveData<Boolean> = _statusPercentage

    private val _afterPriceLocked = MutableLiveData<Long>(0)
    val afterPriceLocked: LiveData<Long> = _afterPriceLocked

    private val _condition = MutableLiveData(Condition.NONE)
    val condition: LiveData<Condition> = _condition

    private val _isAfterPriceLocked = MutableLiveData(false)
    val isAfterPriceLocked: LiveData<Boolean> get() = _isAfterPriceLocked

    private val _isBuyFreeEditable = MutableLiveData(true)
    val isBuyFreeEditable: LiveData<Boolean> get() = _isBuyFreeEditable

    private val _isPercentageEdit = MutableLiveData(false)
    val isPercentageEdit: LiveData<Boolean> get() = _isPercentageEdit

    val totalPrice: LiveData<Long> = MediatorLiveData<Long>().apply {
        fun update() {
            val curentCondition = condition.value ?: Condition.NONE
            val statusPercentage = statusPercentage.value ?: false
            value = when (curentCondition) {
                Condition.NONE -> {
                    if (statusPercentage) {
                        calculateAfterPriceLocked()
                    } else {
                        calculatePriceManually()
                    }
                }

                Condition.BUY_ITEM_FREE_ITEM -> {
                    calculateBuyFreeItem()
                }

                Condition.NO_DISCOUNT -> {
                    calculateNoDiscountPrice()
                }
            }
        }

        addSource(_quantity) { update() }
        addSource(_originalPrice) { update() }
        addSource(_afterPriceManual) { update() }
        addSource(_quatityItemFree) { update() }
        addSource(_quantityBuyFree) { update() }
        addSource(_afterPriceLocked) { update() }
        addSource(_condition) { update() }
        addSource(_statusPercentage) { update() }
        update()
    }

    val savingPrice: LiveData<Long> = MediatorLiveData<Long>().apply {
        fun update() {
            val curentCondition = condition.value ?: Condition.NONE
            val statusPercentage = statusPercentage.value ?: false
            value = when (curentCondition) {
                Condition.NONE -> {
                    if (statusPercentage) {
                        calculateSavingPriceAfterPriceLocked()
                    } else {
                        calculateSavingPriceManually()
                    }
                }

                Condition.BUY_ITEM_FREE_ITEM -> {
                    calculateSavingFreeItem()
                }

                Condition.NO_DISCOUNT -> {
                    0L
                }
            }
        }

        addSource(_quantity) { update() }
        addSource(_afterPriceManual) { update() }
        addSource(_originalPrice) { update() }
        addSource(_quatityItemFree) { update() }
        addSource(_quantityBuyFree) { update() }
        addSource(_afterPriceLocked) { update() }
        addSource(_condition) { update() }
        addSource(_statusPercentage) { update() }
        update()
    }

    fun incrementQuantity() {
        _quantity.value = _quantity.value?.plus(1)
    }

    fun decrementQuantity() {
        if (_quantity.value!! > 1) {
            _quantity.value = _quantity.value?.minus(1)
        } else {
            _quantity.value = 1
        }
    }

    private fun calculateSavingPriceManually(): Long {
        val originPrice = _originalPrice.value ?: 0L
        val afterPriceManually = _afterPriceManual.value ?: 0L
        val quantity = _quantity.value ?: 0
        return menghitungSavingDiskon(originPrice, afterPriceManually, quantity)
    }

    private fun calculateSavingFreeItem(): Long {
        val originPrice = _originalPrice.value ?: 0L
        val quantity = _quatityItemFree.value ?: 1
        return hitungHarga(originPrice, quantity)
    }

    private fun calculateSavingPriceAfterPriceLocked(): Long {
        val originPrice = _originalPrice.value ?: 0L
        val afterPriceLocked = _afterPriceLocked.value ?: 0L
        val quantity = _quantity.value ?: 0
        return menghitungSavingDiskon(originPrice, afterPriceLocked, quantity)

    }

    fun setCondition(condition: Condition) {
        when (condition) {
            Condition.NONE -> {
                _statusPercentage.value = false
                _isAfterPriceLocked.value = false
                _isBuyFreeEditable.value = true
                _isPercentageEdit.value = false
                _condition.value = Condition.NONE
            }

            Condition.BUY_ITEM_FREE_ITEM -> {
                _isAfterPriceLocked.value = true
                _isBuyFreeEditable.value = false
                _isPercentageEdit.value = true
                _afterPriceLocked.value = 0
                _condition.value = Condition.BUY_ITEM_FREE_ITEM
            }

            Condition.NO_DISCOUNT -> {
                _isAfterPriceLocked.value = true
                _isBuyFreeEditable.value = true
                _isPercentageEdit.value = true
                _afterPriceLocked.value = 0
                _condition.value = Condition.NO_DISCOUNT
            }
        }
    }


    fun setStatusPercentage(status: Boolean) {
        _statusPercentage.value = status
    }

    fun setCountDiscount(discount: String, originPrice: String) {
        val discountSet = discount.toIntOrNull() ?: 100
        val discountDouble = discountSet.toDecimalPercetage()
        val originPriceSet = originPrice.fromRupiah()
        _afterPriceLocked.value = setPriceDiscount(discountDouble, originPriceSet)
    }

    fun resetAfterPriceLocked() {
        _afterPriceLocked.value = 0
    }

    private fun calculateNoDiscountPrice(): Long {
        val price = _originalPrice.value ?: 0L
        val quantity = quantity.value ?: 1
        return hitungHarga(price, quantity)
    }

    private fun calculatePriceManually(): Long {
        val price = _afterPriceManual.value ?: 0L
        val quantity = _quantity.value ?: 1
        return hitungHarga(price, quantity)
    }

    private fun calculateBuyFreeItem(): Long {
        val price = _originalPrice.value ?: 0L
        val quantity = _quantityBuyFree.value ?: 1
        return hitungHarga(price, quantity)
    }

    private fun calculateAfterPriceLocked(): Long {
        val price = _afterPriceLocked.value ?: 0L
        val quantity = _quantity.value ?: 1
        return hitungHarga(price, quantity)
    }

    fun setOriginalPrice(price: String) {
        _originalPrice.value = price.fromRupiah()
    }

    fun setQuantityBuyFree(quantity: String) {
        val quantityInt = quantity.toIntOrNull() ?: 1
        _quantityBuyFree.value = quantityInt
    }

    fun setQuantityItemFree(quantity: String) {
        val quantityInt = quantity.toIntOrNull() ?: 1
        _quatityItemFree.value = quantityInt
    }

    fun setAfterPriceManually(afterPrice: String) {
        _afterPriceManual.value = afterPrice.fromRupiah()
    }

}
