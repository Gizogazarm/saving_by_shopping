package com.example.savingbyshopping.ui.addItemShop

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.savingbyshopping.databinding.FragmentAddItemShopBinding


class AddItemShopFragment : Fragment() {

    private var _binding: FragmentAddItemShopBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddItemShopBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val dataShopping = AddItemShopFragmentArgs.fromBundle(arguments as Bundle).dataShopping
        Log.d("Data Shopping", "print data: $dataShopping ")

    }


}