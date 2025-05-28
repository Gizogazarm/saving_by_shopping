package com.example.savingbyshopping.ui.addItemShop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import com.example.savingbyshopping.R
import com.example.savingbyshopping.databinding.FragmentDialogAddItemShopBinding
import com.example.savingbyshopping.utils.JenisProduk


class DialogAddItemShopFragment : DialogFragment() {

    private var _binding: FragmentDialogAddItemShopBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        val list = JenisProduk.entries.map { it.value }
        val adapter = ArrayAdapter(requireContext(), R.layout.dropdown_list_jenis_produk, list)

        with(binding) {

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
            fixedWidthInPx,
            fixedHeightInPx
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}