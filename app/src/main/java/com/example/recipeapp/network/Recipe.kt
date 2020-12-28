package com.example.recipeapp.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

data class Meals(val meals: List<Recipe>)

@Parcelize
data class Recipe(
    @Json(name = "idMeal") val id: Int,
    @Json(name = "strMeal") val name: String,
    @Json(name = "strMealThumb") val thumbnail: String): Parcelable {
        var isFavourite: Boolean = false
    }