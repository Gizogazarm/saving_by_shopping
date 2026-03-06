package com.example.savingbyshopping.ui.homeActivity

import android.os.Bundle
import android.transition.TransitionManager
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.savingbyshopping.R
import com.example.savingbyshopping.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {

            val navController = findNavController(R.id.nav_host_fragment)
            bottomNavigation.setupWithNavController(navController)

            navController.addOnDestinationChangedListener { _, destination, _ ->

                val isTopMenu = bottomNavigation.menu.findItem(destination.id) != null
                TransitionManager.beginDelayedTransition(binding.root as ViewGroup)
                bottomNavigation.visibility = if (isTopMenu) View.VISIBLE else View.GONE
            }


        }


    }


}