package com.example.savingbyshopping.ui.homeActivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.example.savingbyshopping.R
import com.example.savingbyshopping.databinding.ActivityHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var navView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {

            navView = bottomNavigation
            val navController = findNavController(R.id.nav_host_fragment)
            val navOptions = NavOptions.Builder()
                .setLaunchSingleTop(true)
                .setPopUpTo(navController.graph.startDestinationId, false)
                .build()

            navView.setOnItemSelectedListener { item ->

                when (item.itemId) {
                    R.id.homeFragment -> {
                        navController.navigate(R.id.homeFragment, null, navOptions)
                        true
                    }

                    R.id.savingFragment -> {
                        navController.navigate(R.id.savingFragment, null, navOptions)
                        true
                    }

                    R.id.profileFragment -> {
                        navController.navigate(R.id.profileFragment, null, navOptions)
                        true
                    }

                    else -> false
                }


            }

        }


    }


}