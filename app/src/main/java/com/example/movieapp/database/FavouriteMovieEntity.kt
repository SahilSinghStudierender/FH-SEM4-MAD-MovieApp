package com.example.movieapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_movies_table", indices = [Index(value = ["title"], unique = true)])
data class FavouriteMovieEntity(
    @ColumnInfo
    var title: String? = "No title",
    @ColumnInfo
    var note: String = ""
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
}
