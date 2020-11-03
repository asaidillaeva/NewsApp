package com.example.newsapp.db

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newsapp.model.Article

@Database(entities = [Article::class], version = 1, exportSchema = false)
abstract class NewsDB : RoomDatabase() {
    //    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
    //        @Override
    //        public void migrate(@NonNull SupportSQLiteDatabase database) {
    ////            database.execSQL("");
    //        }
    //    };
    abstract fun newsDao(): NewsDAO?

    companion object {
        private val LOG_TAG = NewsDB::class.java.simpleName
        private var db: NewsDB? = null
        @JvmStatic
        fun getDatabase(context: Context): NewsDB? {
            if (db == null) {
                Log.i(LOG_TAG, "No database found, a new will be created")
                db = Room.databaseBuilder(
                        context.applicationContext,
                        NewsDB::class.java,
                        "article")
                        .fallbackToDestructiveMigration()
                        .build()
            } else {
                Log.i(LOG_TAG, "getStoredDatabase: a database already exists")
            }
            return db
        }
    }
}