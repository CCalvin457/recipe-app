package com.example.recipeapp.recipes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.database.DatabaseRecipe
import com.example.recipeapp.database.FoodDatabaseDao
import com.example.recipeapp.network.FoodCategory
import com.example.recipeapp.network.MealDBApi
import com.example.recipeapp.network.Recipe
import com.example.recipeapp.utils.MealApiStatus
import kotlinx.coroutines.launch

class RecipesViewModel(dataSource: FoodDatabaseDao,
                       foodCategory: FoodCategory): ViewModel() {
    val database = dataSource
    private val _recipesList = MutableLiveData<List<Recipe>>()
    val recipesList: LiveData<List<Recipe>>
        get() = _recipesList

    private val _status = MutableLiveData<MealApiStatus>()
    val status: LiveData<MealApiStatus>
        get() = _status

    private val favouritesList = MutableLiveData<List<DatabaseRecipe>>()

    private val _navigateToSelectedRecipe = MutableLiveData<Recipe>()
    val navigateToSelectedRecipe: LiveData<Recipe>
        get() = _navigateToSelectedRecipe



    init {
        getFavouriteRecipes()
        getRecipesByCategory(foodCategory)
    }

    fun getRecipesByCategory(foodCategory: FoodCategory) {
        _status.value = MealApiStatus.LOADING
        viewModelScope.launch {
            try {
                _recipesList.value =
                    MealDBApi.retrofitService.getRecipesByCategory(foodCategory.category).meals

                // TODO: Check for favourites against database ?
                favouritesList.value?.let { favouritesList ->
                    for(recipe in favouritesList) {
                        recipe.recipeId?.let {
                            _recipesList.value?.find { it.id == recipe.recipeId }?.isFavourite = true
                        }
                    }
                }
                _status.value = MealApiStatus.DONE
            } catch(e: Exception) {
                _status.value = MealApiStatus.ERROR
                _recipesList.value = null
            }
        }
    }

    private fun getFavouriteRecipes() {
        viewModelScope.launch {
            favouritesList.value = database.getFavouritesStatic()
        }
    }

    fun displayRecipeDetails(recipe: Recipe) {
        _navigateToSelectedRecipe.value = recipe
    }

    fun displayRecipeDetailsCompleted() {
        _navigateToSelectedRecipe.value = null
    }
}