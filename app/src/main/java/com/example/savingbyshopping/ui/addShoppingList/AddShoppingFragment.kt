package com.example.savingbyshopping.ui.addShoppingList


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.savingbyshopping.R
import com.example.savingbyshopping.data.response.ShoppingList
import com.example.savingbyshopping.databinding.FragmentAddShoppingBinding
import com.example.savingbyshopping.ui.ViewModelFactory
import com.example.savingbyshopping.utils.CalendarUtils
import com.example.savingbyshopping.utils.ambilDuaKataPertama
import com.google.android.material.snackbar.Snackbar

class AddShoppingFragment : Fragment() {

    private var _binding: FragmentAddShoppingBinding? = null
    private lateinit var factory: ViewModelFactory
    private val viewModel: AddShoppingListViewModel by viewModels { factory }
    private lateinit var namaToko: String
    private lateinit var shoppingList: ShoppingList
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddShoppingBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            setViewModelFactory()
            val calendar = CalendarUtils.getCalendarDate()
            val date = CalendarUtils.calendarToString(calendar)

            edDate.setText(CalendarUtils.calendarToString((calendar)))

            btnAddShopping.setOnClickListener {
                if (!edNamaToko.isValid(getString(R.string.error_namaToko))) return@setOnClickListener
                if (!edEmail.isValid(getString(R.string.error_email)) || !edEmail.validateEmail()) return@setOnClickListener

                namaToko = edNamaToko.text.toString().ambilDuaKataPertama()
                val email = edEmail.text.toString()

                shoppingList = ShoppingList(
                    tanggalTransaksi = date,
                    namaToko = namaToko,
                    email = email
                )
                viewModel.inputShoppingList(shoppingList)
                viewModel.insertResult.observe(viewLifecycleOwner) { id ->
                    makeSnackbar(getString(R.string.success_addingShoppingReceipt))
                    val action =
                        AddShoppingFragmentDirections.actionAddShoppingFragmentToAddItemShopFragment(
                            id
                        )
                    view.findNavController().navigate(action)

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

    private fun makeSnackbar(message: String) {
        val bottomNav = requireActivity().findViewById<View>(R.id.bottom_navigation)

        Snackbar.make(requireContext(), binding.root, message, Snackbar.LENGTH_SHORT)
            .setAnchorView(bottomNav)
            .show()


    }


}