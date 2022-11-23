package com.example.kotlin_newsapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.kotlin_newsapp.models.Article

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun Insert(article: Article): Long

    @Query("select * from article")
    fun getAllArticle(): LiveData<List<Article>>

    @Delete
    suspend fun DeleteArtical(article: Article)
}