package com.example.kotlin_newsapp.ui

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foulynewsapp.models.newsResponse
import com.example.kotlin_newsapp.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel
@Inject
constructor(private val repository: Repository):ViewModel(){

    private val _breakingNews=MutableLiveData<newsResponse>()
    val BreakingNews:LiveData<newsResponse>
            get()=_breakingNews
    init {
        getBreakingNews()
    }

    private fun getBreakingNews()=viewModelScope.launch {
        repository.getBreakingNews().let {response ->
            if(response.isSuccessful){
                _breakingNews.postValue(response.body())
            }
        }
    }


}