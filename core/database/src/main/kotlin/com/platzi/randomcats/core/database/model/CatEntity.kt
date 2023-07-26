package com.platzi.randomcats.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CatEntity(
    @PrimaryKey(autoGenerate = true)
    val index: Int? = null,
    val id: String,
    val url: String,
    val width: Int,
    val height: Int
)