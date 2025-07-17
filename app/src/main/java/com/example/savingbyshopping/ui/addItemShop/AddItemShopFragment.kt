package com.example.savingbyshopping.ui.addItemShop

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.savingbyshopping.data.adapter.ListItemShopAdapter
import com.example.savingbyshopping.data.response.ItemShop
import com.example.savingbyshopping.databinding.FragmentAddItemShopBinding
import com.example.savingbyshopping.ui.ViewModelFactory
import com.example.savingbyshopping.ui.addShoppingList.AddShoppingListViewModel
import com.example.savingbyshopping.utils.fromRupiah
import com.example.savingbyshopping.utils.observeOnce
import com.example.savingbyshopping.utils.toRupiah


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
                    tvAmountTotalShopping.text =
                        itemShopViewModel.calculateAllItemPrice(it.itemShop).toRupiah()
                    tvAmountTotalSaving.text =
                        itemShopViewModel.calculateAllItemDiscount(it.itemShop).toRupiah()
                    showRv(it.itemShop)
                }

            btnAddItem.setOnClickListener {
                val action =
                    AddItemShopFragmentDirections.actionAddItemShopFragmentToDialogAddItemShopFragment(
                        idShopping
                    )
                view.findNavController().navigate(action)
            }

            btnCheckout.setOnClickListener {
                val totalBelanja = tvAmountTotalShopping.text.toString().fromRupiah()
                val totalDiskon = tvAmountTotalSaving.text.toString().fromRupiah()

                if (totalBelanja == 0L) {
                    Toast.makeText(requireContext(), "Data Belanja Masih Kosong", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }


                shoppingViewModel.ambilShoppingListDenganId(idShopping).observeOnce(viewLifecycleOwner) { shoppingList ->
                    if (shoppingList != null) {
                        val updatedShoppingList = shoppingList.copy(
                            totalBelanja = totalBelanja,
                            totalDiskon = totalDiskon
                        )

                        shoppingViewModel.perbaruiShoppingList(updatedShoppingList)


                        val action = AddItemShopFragmentDirections.actionAddItemShopFragmentToHomeFragment()
                        view.findNavController().navigate(action)

                    } else {
                        Toast.makeText(requireContext(), "Gagal mengambil data ShoppingList", Toast.LENGTH_SHORT).show()
                    }
                }
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
            adapter.setOnItemClickListener(object : ListItemShopAdapter.OnItemClickListener {
                override fun onItemPlus(item: ItemShop) {
                    val newItem = itemShopViewModel.plusItemQuantity(item)
                    itemShopViewModel.perbaruiItemShop(newItem)
                }

                override fun onItemMinus(item: ItemShop) {
                    val newItem = itemShopViewModel.minItemQuantity(item)
                    itemShopViewModel.perbaruiItemShop(newItem)
                }

                override fun onItemDelete(item: ItemShop) {
                    itemShopViewModel.hapusItemShop(item)
                }

                override fun onItemClick(item: ItemShop) {
                    Toast.makeText(requireContext(), "TESTING DULU", Toast.LENGTH_SHORT).show()
                }

                override fun onUpdateQuantity(item: ItemShop, newqty: Int) {
                    val newItem = itemShopViewModel.setQuantity(item, newqty)
                    itemShopViewModel.perbaruiItemShop(newItem)
                }


            })

            adapter.submitList(itemShop)
            binding.rvItemShop.adapter = adapter

        } else {
            binding.rvItemShop.visibility = View.GONE
            binding.emptyList.visibility = View.VISIBLE
        }
    }

}