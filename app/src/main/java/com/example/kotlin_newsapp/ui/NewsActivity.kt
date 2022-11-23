package com.example.kotlin_newsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.kotlin_newsapp.R
import com.example.kotlin_newsapp.databinding.ActivityNewsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsActivity : AppCompatActivity() {
    lateinit var binding: ActivityNewsBinding
    private val viewmodel:NewsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        connectBottomNavBarWithFragments()

    }

    fun connectBottomNavBarWithFragments(){
        val navController=findNavController(R.id.newsNavHostFragment)
        //السطرين اللي تحت بعض دول بيخلو اسم الاكتيفتي يتغير ويبقي نفس اسم الفراجمنت كل م اقلب
        val appbarconf= AppBarConfiguration(setOf(R.id.breakingNewsFragment2,R.id.savedNewsFragment2,R.id.searchNewsFragment2))
        setupActionBarWithNavController(navController,appbarconf)
        //__________________________________________________________
        binding.bottomNavigationView.setupWithNavController(navController)
    }
}