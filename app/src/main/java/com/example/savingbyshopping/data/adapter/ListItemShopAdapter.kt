package com.example.savingbyshopping.data.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.savingbyshopping.data.response.ItemShop
import com.example.savingbyshopping.databinding.ItemshopListBinding
import com.example.savingbyshopping.utils.Condition
import com.example.savingbyshopping.utils.toRupiah

class ListItemShopAdapter :
    ListAdapter<ItemShop, ListItemShopAdapter.ItemShopViewHolder>(DiffCallback) {

    private lateinit var onItemClickListener: OnItemClickListener

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }


    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<ItemShop>() {
            override fun areItemsTheSame(oldItem: ItemShop, newItem: ItemShop): Boolean {
                return oldItem.idItem == newItem.idItem
            }

            override fun areContentsTheSame(oldItem: ItemShop, newItem: ItemShop): Boolean {
                return oldItem == newItem
            }

        }

    }

    class ItemShopViewHolder(
        private val binding: ItemshopListBinding,
        private val onItemClickListener: OnItemClickListener
    ) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(itemShop: ItemShop) {
            with(binding) {
                tvTitleItemList.text = itemShop.namaItem
                tvQuantityItemList.text = "${itemShop.quantity} PCS"
                tvQuantityItem.text = itemShop.quantity.toString()
                tvAmounTotalShopItemList.text = itemShop.totalHarga.toRupiah()
                tvTotalSavingItemList.text = "+ ${itemShop.saveDiskon.toRupiah()}"
                tvStatusItemList.text = setCondition(itemShop.condition)

                btnDeleteItemShop.setOnClickListener {
                    onItemClickListener.onItemDelete(itemShop)
                }

                btnAddQtyItem.setOnClickListener {
                    onItemClickListener.onItemPlus(itemShop)
                }

                btnMinQtyItem.setOnClickListener {
                    onItemClickListener.onItemMinus(itemShop)
                }

            }
        }

        private fun setCondition(condition: Condition): String {
            return when (condition) {
                Condition.NONE -> "(Discount)"
                Condition.BUY_ITEM_FREE_ITEM -> "(Free Item)"
                Condition.NO_DISCOUNT -> "(No Discount)"
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemShopViewHolder {
        val binding =
            ItemshopListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemShopViewHolder(binding,onItemClickListener)

    }

    override fun onBindViewHolder(holder: ItemShopViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(item)
        }
        holder.bind(item)
    }

    interface OnItemClickListener {
        fun onItemPlus(item: ItemShop)
        fun onItemMinus(item: ItemShop)
        fun onItemDelete(item: ItemShop)
        fun onItemClick(item: ItemShop)
    }

}