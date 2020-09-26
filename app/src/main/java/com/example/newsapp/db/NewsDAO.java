package com.example.newsapp.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.newsapp.model.Article;

import java.util.List;

@Dao
public interface NewsDAO {
    @Query("SELECT * FROM Article WHERE nid = :id LIMIT 1")
    Article findArticleById(long id);

    @Query("DELETE FROM Article WHERE nid = :id")
    void delete(long id);


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(Article art);

    @Query("DELETE FROM Article")
    void deleteAll();

    @Query("SELECT * FROM Article ORDER BY publishedAt DESC")
    List<Article> getAllArticles();
}
