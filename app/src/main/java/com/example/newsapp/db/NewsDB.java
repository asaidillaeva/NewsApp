package com.example.newsapp.db;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.newsapp.model.Article;

@Database(entities = {Article.class}, version = 1, exportSchema = false)
public abstract class NewsDB extends RoomDatabase {

    private static String LOG_TAG = NewsDB.class.getSimpleName();
    private static NewsDB db;

//    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
//        @Override
//        public void migrate(@NonNull SupportSQLiteDatabase database) {
////            database.execSQL("");
//        }
//    };

    public abstract NewsDAO newsDao();


    public static NewsDB getDatabase(Context context) {
        if (db == null) {
            Log.i(LOG_TAG, "No database found, a new will be created");
            db = Room.databaseBuilder(
                    context.getApplicationContext(),
                    NewsDB.class,
                    "article")
                    .fallbackToDestructiveMigration()
                    .build();
        } else {
            Log.i(LOG_TAG, "getStoredDatabase: a database already exists");
        }
        return db;
    }
}
