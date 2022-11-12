package com.example.foulynewsapp.ui.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foulynewsapp.adapters.NewsAdapter
import com.example.kotlin_newsapp.databinding.ActivityNewsBinding
import com.example.kotlin_newsapp.databinding.FragmentBreakingNewsBinding
import com.example.kotlin_newsapp.ui.NewsActivity
import com.example.kotlin_newsapp.ui.NewsViewModel

class BreakingNewsFragment:Fragment() {
    lateinit var binding: FragmentBreakingNewsBinding
    private val viewmodel:NewsViewModel by viewModels()
    lateinit var adapt:NewsAdapter

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        binding= FragmentBreakingNewsBinding.inflate(inflater,container,false)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()


    }

    private fun setUpRecyclerView() {
        adapt=NewsAdapter()
        binding.rvBreakingNews.apply {
            adapter=adapt
            setHasFixedSize(true)
        }
        viewmodel.BreakingNews.observe(viewLifecycleOwner, Observer{ response ->
            if(response!=null){
                adapt.differ.submitList(response.articles)
            }

        })
    }


}