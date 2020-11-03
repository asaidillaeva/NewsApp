package com.example.newsapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.model.Article

@Dao
 open interface NewsDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(art: Article?): Long

    @Query("DELETE FROM Article")
    fun deleteAll()

    @get:Query("SELECT * FROM Article ORDER BY publishedAt DESC")
    var allArticles: List<Article?>?
}