package com.example.savingbyshopping.ui.addItemShop

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.savingbyshopping.databinding.FragmentAddItemShopBinding
import com.example.savingbyshopping.ui.ViewModelFactory
import com.example.savingbyshopping.ui.addShoppingList.AddShoppingListViewModel


class AddItemShopFragment : Fragment() {

    private var _binding: FragmentAddItemShopBinding? = null
    private val binding get() = _binding!!
    private lateinit var factory : ViewModelFactory
    private val shoppingViewModel: AddShoppingListViewModel by viewModels { factory }
    private val itemShopViewModel: AddItemShopViewModel by viewModels { factory }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddItemShopBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        setViewModelFactory()
        val idShopping = AddItemShopFragmentArgs.fromBundle(arguments as Bundle).idShopping
        shoppingViewModel.ambilShoppingListDenganId(idShopping).observe(viewLifecycleOwner) {
            Log.d("Data Shopping", "print data: $it ")
        }


    }

    private fun setViewModelFactory() {
        factory = ViewModelFactory.getInstance(requireActivity())
    }

}