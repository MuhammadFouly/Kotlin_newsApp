package com.example.kotlin_newsapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.kotlin_newsapp.R
import com.example.kotlin_newsapp.adapters.NewsAdapter
import com.example.kotlin_newsapp.databinding.FragmentBreakingNewsBinding
import com.example.kotlin_newsapp.ui.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
// TODO look at the parent fragment constructor...We pass the layout id
//  instead of overriding onCreateView and init the binding in onViewCreated method
class BreakingNewsFragment : Fragment(R.layout.fragment_breaking_news) {
    private lateinit var binding: FragmentBreakingNewsBinding
    private val viewModel: NewsViewModel by viewModels()
    lateinit var adapt: NewsAdapter

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?,
//    ): View? {
//        binding = FragmentBreakingNewsBinding.inflate(inflater, container, false)
//        return binding.root
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBreakingNewsBinding.bind(view)
        Log.e("TestTag", "BreakingNewsFragment: ")

        setUpRecyclerView()


    }

    private fun setUpRecyclerView() {
        adapt = NewsAdapter()
        binding.rvBreakingNews.apply {
            adapter = adapt
            setHasFixedSize(true)
        }
        viewModel.breakingNews.observe(viewLifecycleOwner) { response ->
            if (response != null) {
                adapt.differ.submitList(response.articles)
            }
        }
    }


}