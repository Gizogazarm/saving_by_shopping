package com.example.savingbyshopping.ui.addItemShop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.savingbyshopping.R
import com.example.savingbyshopping.databinding.FragmentDialogAddItemShopBinding
import com.example.savingbyshopping.ui.ViewModelFactory
import com.example.savingbyshopping.utils.JenisProduk


class DialogAddItemShopFragment : DialogFragment() {

    private var _binding: FragmentDialogAddItemShopBinding? = null
    private val binding get() = _binding!!
    private lateinit var factory: ViewModelFactory
    private val itemShopViewModel: AddItemShopViewModel by activityViewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
        setStyle(STYLE_NORMAL, R.style.Theme_SavingByShopping_FullScreenDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDialogAddItemShopBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setViewModelFactory()
        val list = JenisProduk.entries.map { it.value }
        val adapter = ArrayAdapter(requireContext(), R.layout.dropdown_list_jenis_produk, list)

        with(binding) {

            btnCheckBuyItemFreeItem.isEnabled = false

            btnAddQty.setOnClickListener {
                itemShopViewModel.incrementQuantity()
            }

            btnMinQty.setOnClickListener {
                itemShopViewModel.decrementQuantity()
            }

            itemShopViewModel.quantity.observe(viewLifecycleOwner) {
                tvQuantity.text = it.toString()
            }

            //BAGIAN QUANTITY TAMBAH DAN KURANG

            rbNone.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    rbBuyOneFree.isChecked = false
                    rbNoDiscount.isChecked = false
                }
                visibleEditTextAfterPrice(!isChecked)
                visibleEditTextBuyFreeitem(isChecked)
            }

            rbBuyOneFree.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    rbNone.isChecked = false
                    rbNoDiscount.isChecked = false
                }
                visibleEditTextAfterPrice(isChecked)
                visibleEditTextBuyFreeitem(!isChecked)
            }

            rbNoDiscount.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    rbNone.isChecked = false
                    rbBuyOneFree.isChecked = false
                }
                visibleEditTextAfterPrice(isChecked)
                visibleEditTextBuyFreeitem(isChecked)
            }

            btnCancel.setOnClickListener {
                dismiss()
            }

            edJenisProduk.setAdapter(adapter)

        }
    }

    override fun onStart() {
        super.onStart()
        val fixedHeightInPx = resources.getDimensionPixelSize(R.dimen.my_dialog_fixed_height)
        val fixedWidthInPx = resources.getDimensionPixelSize(R.dimen.my_dialog_fixed_width)

        dialog?.window?.setLayout(
            fixedWidthInPx, fixedHeightInPx
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setViewModelFactory() {
        factory = ViewModelFactory.getInstance(requireActivity())
    }

    private fun visibleEditTextAfterPrice(status: Boolean) {
        with(binding) {
            if (status) {
                inputAfterPriceLock.visibility = View.VISIBLE
                inputAfterPrice.visibility = View.GONE
            } else {
                inputAfterPriceLock.visibility = View.GONE
                inputAfterPrice.visibility = View.VISIBLE
            }
        }
    }

    private fun visibleEditTextBuyFreeitem(status: Boolean) {
        with(binding) {
            if (status) {
                edBuyItem.isEnabled = false
                edFreeItem.isEnabled = false
                btnCheckBuyItemFreeItem.isEnabled = false
            } else {
                edBuyItem.isEnabled = true
                edFreeItem.isEnabled = true
                btnCheckBuyItemFreeItem.isEnabled = true
            }
        }
    }


}