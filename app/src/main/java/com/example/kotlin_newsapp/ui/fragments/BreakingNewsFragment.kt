package com.example.foulynewsapp.ui.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foulynewsapp.adapters.NewsAdapter
import com.example.kotlin_newsapp.databinding.ActivityNewsBinding
import com.example.kotlin_newsapp.databinding.FragmentBreakingNewsBinding
import com.example.kotlin_newsapp.ui.NewsActivity
import com.example.kotlin_newsapp.ui.NewsViewModel

class BreakingNewsFragment:Fragment() {
    lateinit var binding: FragmentBreakingNewsBinding
    private val viewmodel:NewsViewModel by viewModels()
    private val adapt=NewsAdapter()
    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        binding= FragmentBreakingNewsBinding.inflate(inflater,container,false)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()


    }

    private fun setUpRecyclerView() {
    binding.rvBreakingNews.apply {
            adapter=adapt
            layoutManager=LinearLayoutManager(context)
            setHasFixedSize(true)
        }
        viewmodel.BreakingNews.observe(viewLifecycleOwner, Observer{ response ->
            if(response!=null){
                adapt.differ.submitList(response.articles)
            }
        })

    }


}
//lateinit var viewmodel:NewsViewModel
//viewmodel=ViewModelProvider(this).get(NewsViewModel::class.java)