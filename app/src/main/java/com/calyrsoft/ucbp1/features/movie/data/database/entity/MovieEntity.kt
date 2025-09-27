package com.calyrsoft.ucbp1.features.movie.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey(autoGenerate = false)
    val title: String,   // lo usamos como clave única (podrías usar un id si la API lo da)
    val pathUrl: String,
    val isLiked: Boolean = false
)
