package com.example.savingbyshopping.ui.addItemShop

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.savingbyshopping.data.adapter.ListItemShopAdapter
import com.example.savingbyshopping.data.response.ItemShop
import com.example.savingbyshopping.databinding.FragmentAddItemShopBinding
import com.example.savingbyshopping.ui.ViewModelFactory
import com.example.savingbyshopping.ui.addShoppingList.AddShoppingListViewModel


class AddItemShopFragment : Fragment() {

    private var _binding: FragmentAddItemShopBinding? = null
    private val binding get() = _binding!!
    private lateinit var factory: ViewModelFactory
    private val shoppingViewModel: AddShoppingListViewModel by viewModels { factory }
    private val itemShopViewModel: AddItemShopViewModel by activityViewModels { factory }


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

        with(binding) {
            shoppingViewModel.ambilShoppingListDenganItemShopById(idShopping)
                .observe(viewLifecycleOwner) {
                    tvToko1.text = it.shoppingList.namaToko
                    tvEmail.text = it.shoppingList.email
                    tvDate.text = it.shoppingList.tanggalTransaksi
                    tvAmountTotalShopping.text = it.shoppingList.totalBelanja
                    tvAmountTotalSaving.text = it.shoppingList.totalDiskon
                    showRv(it.itemShop)
                    Log.d("data shopping list", "data shopping list $it")
                }

            btnAddItem.setOnClickListener {
                val action = AddItemShopFragmentDirections.actionAddItemShopFragmentToDialogAddItemShopFragment(idShopping)
                view.findNavController().navigate(action)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setViewModelFactory() {
        factory = ViewModelFactory.getInstance(requireActivity())
    }

    private fun showRv(itemShop: List<ItemShop>?) {
        if (!itemShop.isNullOrEmpty()) {
            binding.rvItemShop.visibility = View.VISIBLE
            binding.emptyList.visibility = View.GONE
            val adapter = ListItemShopAdapter()
            adapter.submitList(itemShop)
            binding.rvItemShop.adapter = adapter

        } else {
            binding.rvItemShop.visibility = View.GONE
            binding.emptyList.visibility = View.VISIBLE
        }
    }

}