package com.example.kotlin_newsapp.db

import androidx.room.TypeConverter
import com.example.kotlin_newsapp.models.Source

class Converter {
    @TypeConverter
    fun fromSourceToString(source: Source):String{
        return source.name
    }
    @TypeConverter
    fun fromStringToSource(name:String): Source {
        return Source(name, name)
    }
}