package com.example.recipeapp.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

/**
 * Data class used to retrieve the categories array
 */
data class Categories(
    val categories: List<FoodCategory>
)

/**
 * Data class for each food category object. Parcelize is used to allow us to pass the
 * FoodCategory object to other fragments or activities
 */
@Parcelize
data class FoodCategory(
    val idCategory: Int,
    @Json(name = "strCategory") val category: String,
    @Json(name = "strCategoryThumb") val img: String,
    @Json(name = "strCategoryDescription") val description: String): Parcelable