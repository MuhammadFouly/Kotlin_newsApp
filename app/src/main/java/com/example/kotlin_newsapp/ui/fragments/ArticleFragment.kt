package com.example.kotlin_newsapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kotlin_newsapp.databinding.FragmentArticleBinding
// TODO don't forget @AndroidEntryPoint
class ArticleFragment:Fragment() {
    lateinit var binding: FragmentArticleBinding

    // TODO no need to override onCreateView just init binding inside onViewCreated and
    //  pass the layout id to the parent fragment constructor see BreakingNewsFragment for that
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=  FragmentArticleBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}