package com.example.kotlin_newsapp.repository

import com.example.kotlin_newsapp.di.NewsAPI
import javax.inject.Inject

class Repository
@Inject
constructor(private val api:NewsAPI) {
    suspend fun getBreakingNews()=api.getBreakingNews()

}