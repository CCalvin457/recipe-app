package com.example.recipeapp.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

data class Categories(
    val categories: List<FoodCategory>
)

@Parcelize
data class FoodCategory(
    val idCategory: Int,
    @Json(name = "strCategory") val category: String,
    @Json(name = "strCategoryThumb") val img: String,
    @Json(name = "strCategoryDescription") val description: String): Parcelable