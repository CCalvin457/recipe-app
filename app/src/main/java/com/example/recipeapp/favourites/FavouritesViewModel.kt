package com.example.recipeapp.favourites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.database.DatabaseRecipe
import com.example.recipeapp.database.FoodDatabaseDao
import com.example.recipeapp.network.Recipe
import com.example.recipeapp.utils.MealDatabaseStatus
import kotlinx.coroutines.launch

class FavouritesViewModel(dataSource: FoodDatabaseDao): ViewModel() {
    val database = dataSource

    val favouritesList = database.getFavourites()

    private val _navigateToRecipeDetails = MutableLiveData<DatabaseRecipe>()
    val navigateToRecipeDetails: LiveData<DatabaseRecipe>
        get() = _navigateToRecipeDetails


    fun displayRecipeDetails(recipe: DatabaseRecipe) {
        _navigateToRecipeDetails.value = recipe
    }

    fun displayRecipeDetailsCompleted() {
        _navigateToRecipeDetails.value = null
    }
}