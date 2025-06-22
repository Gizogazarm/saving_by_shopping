package com.example.savingbyshopping.ui.addItemShop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.savingbyshopping.R
import com.example.savingbyshopping.data.response.ItemShop
import com.example.savingbyshopping.databinding.FragmentDialogAddItemShopBinding
import com.example.savingbyshopping.ui.ViewModelFactory
import com.example.savingbyshopping.utils.Condition
import com.example.savingbyshopping.utils.JenisProduk
import com.example.savingbyshopping.utils.setNotMinus
import com.example.savingbyshopping.utils.toRupiah
import com.google.android.material.snackbar.Snackbar


class DialogAddItemShopFragment : DialogFragment() {

    private var _binding: FragmentDialogAddItemShopBinding? = null
    private val binding get() = _binding!!
    private lateinit var factory: ViewModelFactory
    private val itemShopViewModel: AddItemShopViewModel by activityViewModels { factory }
    private val dialogAddItemShopViewModel: DialogAddItemShopViewModel by viewModels { factory }

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
        val idShopping = DialogAddItemShopFragmentArgs.fromBundle(arguments as Bundle).idShopping
        val list = JenisProduk.entries.map { it.value }
        val adapter = ArrayAdapter(requireContext(), R.layout.dropdown_list_jenis_produk, list)

        with(binding) {
            edJenisProduk.setAdapter(adapter)

            btnCheckBuyItemFreeItem.isEnabled = false // CHECK BUY ITEM FREE ITEM

            edOriginalPrice.addTextChangedListener {
                edAfterPrice.setMaxValueError(edOriginalPrice.text.toString(), getString(R.string.error_notBigger))
                dialogAddItemShopViewModel.setOriginalPrice(edOriginalPrice.text.toString())
            }

            edAfterPrice.addTextChangedListener {
                dialogAddItemShopViewModel.setAfterPriceManually(edAfterPrice.text.toString())
            }

            dialogAddItemShopViewModel.totalPrice.observe(viewLifecycleOwner) {
                tvTotalPriceAmount.text = it.toRupiah()
            }

            dialogAddItemShopViewModel.savingPrice.observe(viewLifecycleOwner) {
                val data = it.setNotMinus().toRupiah()
                val showData = "+ $data"
                tvTotalSavingAmount.text = showData
            }

            dialogAddItemShopViewModel.quantityBuyFree.observe(viewLifecycleOwner) {
                edBuyItem.setText(it.toString())
            }

            dialogAddItemShopViewModel.quantityItemFree.observe(viewLifecycleOwner) {
                edFreeItem.setText(it.toString())
            }

            btnAddQty.setOnClickListener {
                dialogAddItemShopViewModel.incrementQuantity()
            }

            btnMinQty.setOnClickListener {
                dialogAddItemShopViewModel.decrementQuantity()
            }

            dialogAddItemShopViewModel.quantity.observe(viewLifecycleOwner) {
                tvQuantity.text = it.toString()
            }

            dialogAddItemShopViewModel.isAfterPriceLocked.observe(viewLifecycleOwner) {
                visibleEditTextAfterPrice(it)
            }

            dialogAddItemShopViewModel.isBuyFreeEditable.observe(viewLifecycleOwner) {
                visibleEditTextBuyFreeitem(it)
            }

            dialogAddItemShopViewModel.afterPriceLocked.observe(viewLifecycleOwner) {
                edAfterlPriceLock.setText(it.toRupiah())
            }

            dialogAddItemShopViewModel.isPercentageEdit.observe(viewLifecycleOwner) {
                visiblePercentageDialog(it)
            }

            dialogAddItemShopViewModel.statusPercentage.observe(viewLifecycleOwner) {
                visibleSetPercentageDiscount(it)
            }

            rbNone.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    rbBuyOneFree.isChecked = false
                    rbNoDiscount.isChecked = false
                    dialogAddItemShopViewModel.setCondition(Condition.NONE)
                }
            }

            rbBuyOneFree.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    rbNone.isChecked = false
                    rbNoDiscount.isChecked = false
                    dialogAddItemShopViewModel.setCondition(Condition.BUY_ITEM_FREE_ITEM)
                }
            }

            rbNoDiscount.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    rbNone.isChecked = false
                    rbBuyOneFree.isChecked = false
                    dialogAddItemShopViewModel.setCondition(Condition.NO_DISCOUNT)
                }

            }

            btnCancel.setOnClickListener {
                dismiss()
            }

            btnCheckPercentage.setOnClickListener {
                if (!edOriginalPrice.isValid(getString(R.string.error_price))) return@setOnClickListener
                dialogAddItemShopViewModel.setCountDiscount(
                    edPercentageDialog.text.toString(), edOriginalPrice.text.toString()
                )
                dialogAddItemShopViewModel.setStatusPercentage(true)
            }

            btnCancelPercentage.setOnClickListener {
                dialogAddItemShopViewModel.setStatusPercentage(false)
                dialogAddItemShopViewModel.resetAfterPriceLocked()
            }

            btnCheckBuyItemFreeItem.setOnClickListener {
                dialogAddItemShopViewModel.setQuantityBuyFree(edBuyItem.text.toString())
                dialogAddItemShopViewModel.setQuantityItemFree(edFreeItem.text.toString())
            }

            btnSaveItem.setOnClickListener {

                val curentCondition = dialogAddItemShopViewModel.condition.value
                val percentageCondition = dialogAddItemShopViewModel.statusPercentage.value


                if (!edNamaItem.isValid(getString(R.string.error_item))) {
                    Snackbar.make(view, getString(R.string.error_item), Snackbar.LENGTH_SHORT)
                        .show()
                    return@setOnClickListener
                }
                if (!edJenisProduk.isValid(getString(R.string.error_jenisProduk))) {
                    Snackbar.make(
                        view, getString(R.string.error_jenisProduk), Snackbar.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }
                if (!edOriginalPrice.isValid(getString(R.string.error_price))) {
                    Snackbar.make(view, getString(R.string.error_price), Snackbar.LENGTH_SHORT)
                        .show()
                    return@setOnClickListener
                }

                if (curentCondition == Condition.NONE && percentageCondition == false) {
                    if (!edAfterPrice.isValid(getString(R.string.error_afterPrice))) return@setOnClickListener
                    edAfterPrice.setMaxValueError(
                        edOriginalPrice.text.toString(), getString(R.string.error_notBigger)
                    )
                    if (edAfterPrice.getStatusError()) return@setOnClickListener
                }


                itemShopViewModel.inputItemShop(
                    ItemShop(
                        idShoppingList = idShopping,
                        namaItem = edNamaItem.text.toString(),
                        jenisProduk = edJenisProduk.text.toString(),
                        hargaAsli = dialogAddItemShopViewModel.originalPrice.value ?: 0L,
                        hargaDiskon = dialogAddItemShopViewModel.getLatestHargaDiskon(),
                        quantity = dialogAddItemShopViewModel.getLatestQuantity(),
                        totalHarga = dialogAddItemShopViewModel.totalPrice.value ?: 0L,
                        saveDiskon = dialogAddItemShopViewModel.savingPrice.value ?: 0L

                    )
                )


                val action =
                    DialogAddItemShopFragmentDirections.actionDialogAddItemShopFragmentToAddItemShopFragment(
                        idShopping
                    )
                findNavController().navigate(action)
            }


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

    private fun visiblePercentageDialog(status: Boolean) {
        with(binding) {
            when (status) {
                false -> {
                    edPercentageDialog.isEnabled = true
                    btnCheckPercentage.isEnabled = true
                    btnCancelPercentage.isEnabled = true
                }

                true -> {
                    edPercentageDialog.isEnabled = false
                    btnCheckPercentage.isEnabled = false
                    btnCancelPercentage.isEnabled = false
                }
            }
        }

    }

    private fun visibleSetPercentageDiscount(status: Boolean) {
        with(binding) {
            when (status) {
                true -> {
                    inputAfterPriceLock.visibility = View.VISIBLE
                    inputAfterPrice.visibility = View.GONE
                }

                false -> {
                    inputAfterPriceLock.visibility = View.GONE
                    inputAfterPrice.visibility = View.VISIBLE
                }

            }
        }

    }


}