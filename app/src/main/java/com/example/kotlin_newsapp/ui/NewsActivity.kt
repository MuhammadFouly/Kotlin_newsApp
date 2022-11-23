package com.example.kotlin_newsapp.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.kotlin_newsapp.R
import com.example.kotlin_newsapp.databinding.ActivityNewsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsActivity : AppCompatActivity() {
    lateinit var binding: ActivityNewsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.e("TestTag", "NewsActivity: ")

        connectBottomNavBarWithFragments()
    }

    private fun connectBottomNavBarWithFragments() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.newsNavHostFragment) as NavHostFragment
        val navController = navHostFragment.findNavController()
        //السطرين اللي تحت بعض دول بيخلو اسم الاكتيفتي يتغير ويبقي نفس اسم الفراجمنت كل م اقلب
        val appbarconf = AppBarConfiguration(setOf(R.id.breakingNewsFragment2,
            R.id.savedNewsFragment2,
            R.id.searchNewsFragment2))
        setupActionBarWithNavController(navController, appbarconf)
        //__________________________________________________________
        binding.bottomNavigationView.setupWithNavController(navController)
    }
}