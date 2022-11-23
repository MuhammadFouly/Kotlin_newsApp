package com.example.kotlin_newsapp.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin_newsapp.models.NewsResponse
import com.example.kotlin_newsapp.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {
    private val _breakingNews = MutableLiveData<NewsResponse>()
    val breakingNews: LiveData<NewsResponse>
        get() = _breakingNews


    private fun getBreakingNews() = viewModelScope.launch {
        Log.e("TestTag", "getBreakingNews: ")
        repository.getBreakingNews().let { response ->
            if (response.isSuccessful) {
                _breakingNews.postValue(response.body())
            } else {
                Log.d("yourTage", "theBreakingNews${response.errorBody()}")
            }
        }
    }

    init {
        Log.e("TestTag", "init: ")

        getBreakingNews()
    }
}