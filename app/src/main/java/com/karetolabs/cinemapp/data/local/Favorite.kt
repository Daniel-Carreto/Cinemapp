package com.karetolabs.cinemapp.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.karetolabs.cinemapp.Favorite

@Entity(tableName = "favorite_table")
data class Favorite(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    @ColumnInfo(name = "title")
    var title: String?,
    @ColumnInfo(name = "url_image")
    var urlImage: String?,
    var summary: String?,
    var year: String?,
    var genre: String?,
    var duration: String?,
    var uriImage: String? = null,
    var director: String? = null
)
