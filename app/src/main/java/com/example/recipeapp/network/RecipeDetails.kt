package com.example.recipeapp.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

data class Recipes(val meals: List<RecipeDetails>)

@Parcelize
data class RecipeDetails(
    @Json(name = "idMeal") val id: Int,
    @Json(name = "strMeal") val name: String,
    @Json(name = "strDrinkAlternate") val drinkAlt: String,
    @Json(name = "strCategory") val category: String,
    @Json(name = "strArea") val area: String,
    @Json(name = "strInstructions") val instructions: String,
    @Json(name = "strMealThumb") val thumbnail: String,
    @Json(name = "strTags") val tags: String,
    @Json(name = "strYoutube") val video: String
): Parcelable