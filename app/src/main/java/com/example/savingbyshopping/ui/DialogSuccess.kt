package com.example.savingbyshopping.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.savingbyshopping.R
import com.example.savingbyshopping.databinding.FragmentDialogSuccessBinding


class DialogSuccess : DialogFragment() {

    private var _binding: FragmentDialogSuccessBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
        setStyle(STYLE_NORMAL, R.style.Theme_SavingByShopping_FullScreenDialog)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDialogSuccessBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnCclDialogSucces.setOnClickListener {
            dismiss()
        }
    }

    override fun onStart() {
        super.onStart()
        val fixedHeightPx = resources.getDimensionPixelSize(R.dimen.my_dialog_fixed_height1)
        val fixedWidthPx = resources.getDimensionPixelSize(R.dimen.my_dialog_fixed_width1)

        dialog?.window?.setLayout(
            fixedWidthPx, fixedHeightPx
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}