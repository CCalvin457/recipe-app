package com.example.recipeapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe_table")
data class Recipe(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "recipe_id")
    var recipeId: Int = 0,

    @ColumnInfo(name = "recipe_name")
    var recipeName: String = "",

    @ColumnInfo(name = "recipe_thumbnail")
    var thumbnail: String = ""
)