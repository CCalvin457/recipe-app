package com.example.recipeapp.recipeDetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.network.MealDBApi
import com.example.recipeapp.network.RecipeDetails
import com.example.recipeapp.network.Recipes
import com.squareup.moshi.JsonAdapter
import kotlinx.coroutines.launch

class RecipeDetailsViewModel(recipeId: Int): ViewModel() {
    private val _recipe = MutableLiveData<RecipeDetails>()
    private val _recipeDetails = MutableLiveData<RecipeDetails>()
    val recipeDetails: LiveData<RecipeDetails>
        get() = _recipeDetails


    init {
        getRecipe(recipeId)
    }

    private fun getRecipe(recipeId: Int) {
        viewModelScope.launch {
            try {
                _recipe.value = MealDBApi.retrofitService.getRecipe(recipeId)
                Log.d("RecipeDetailsViewModel", "${_recipe.value}")
            } catch(e: Exception) {
                _recipe.value = null
                Log.d("RecipeDetailsViewModel", "ERROR: ${e.message}")
            }
        }
    }
}

