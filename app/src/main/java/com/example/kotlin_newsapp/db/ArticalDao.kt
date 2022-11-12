package com.example.foulynewsapp.db

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.foulynewsapp.models.Article

interface ArticalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun Insert(article: Article):Long
    @Query("select * from article")
    suspend fun getAllArticle():LiveData<ArrayList<Article>>
    @Delete
    suspend fun DeleteArtical(article: Article)
}