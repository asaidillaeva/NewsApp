package com.example.newsapp.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
class Article {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "nid")
    public var id: Long = 0

    @SerializedName("source")
    @Expose
    @Embedded
    public var source: Source? = null

    @SerializedName("author")
    @Expose
    public var author: String? = null

    @SerializedName("title")
    @Expose
    public var title: String? = null

    @SerializedName("description")
    @Expose
    public var description: String? = null

    @SerializedName("url")
    @Expose
    public var url: String? = null

    @SerializedName("urlToImage")
    @Expose
    public var urlToImage: String? = null

    @SerializedName("publishedAt")
    @Expose
    public var publishedAt: String? = null

    @SerializedName("content")
    @Expose
    public var content: String? = null

}