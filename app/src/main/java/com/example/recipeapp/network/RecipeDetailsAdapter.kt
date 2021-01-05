package com.example.recipeapp.network

import android.util.Log
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

class RecipeDetailsAdapter {
    @FromJson
    fun recipeFromJson(recipes: Recipes): Recipes  {
        Log.d("RecipeDetailsAdapter", "testing: $recipes")
        return recipes
    }
}