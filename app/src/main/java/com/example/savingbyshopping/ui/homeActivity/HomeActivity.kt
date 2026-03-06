package com.example.savingbyshopping.ui.homeActivity

import android.os.Bundle
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
            // MASIH ADA BUG DIMANA HOVER TAB SELAIN HOME ACTIVE DAN STUCK KETIKA
            // SUDAH PINDAH DARI HOMEFRAGMENT LALU TEKAN TAB LAIN SAVING LALU TEKAN
            // KEMBALI TAB HOME MAKA HOVER TAB LAIN STUCK


        }


    }


}