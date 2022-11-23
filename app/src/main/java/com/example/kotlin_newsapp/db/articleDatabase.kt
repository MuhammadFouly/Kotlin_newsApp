package com.example.kotlin_newsapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.kotlin_newsapp.models.Article

@Database(
    entities = [Article::class],
    version = 1
)
@TypeConverters(Converter::class)
abstract class ArticleDatabase :RoomDatabase(){
    abstract fun getArticleDao(): ArticleDao

    companion object{
        @Volatile
        private var instance: ArticleDatabase?=null
        private var Lock=Any()
        operator fun invoke(context: Context)= instance ?: synchronized(Lock){
            instance ?: createDatabase(context).also { instance =  it }
        }


        private fun createDatabase(context:Context)=
            Room.databaseBuilder(
                context.applicationContext,
                ArticleDatabase::class.java,
                "Article_db.db"
            ).fallbackToDestructiveMigration()
                .build()
    }




}