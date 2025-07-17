package com.example.savingbyshopping.ui.homeActivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.savingbyshopping.R
import com.example.savingbyshopping.databinding.FragmentHomeBinding
import com.example.savingbyshopping.ui.ViewModelFactory
import com.example.savingbyshopping.ui.addShoppingList.AddShoppingListViewModel


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var factory: ViewModelFactory
    private val shoppingListViewModel: AddShoppingListViewModel by viewModels { factory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(LayoutInflater.from(context), container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        setViewModelFactory()

        with(binding) {
            cardAddShopping.setOnClickListener {
                view.findNavController().navigate(R.id.action_homeFragment_to_addShoppingFragment)
            }

            shoppingListViewModel.ambilSemuaShoppingList().observe(viewLifecycleOwner) {
                amountSaving.text = shoppingListViewModel.calculateAllSavingDiscountUser(it)
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


}