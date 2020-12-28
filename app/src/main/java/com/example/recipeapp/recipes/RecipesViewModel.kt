package com.example.recipeapp.recipes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    init {
        getRecipesByCategory(foodCategory)
    }

    fun getRecipesByCategory(foodCategory: FoodCategory) {
        _status.value = MealApiStatus.LOADING
        viewModelScope.launch {
            try {
                _recipesList.value =
                    MealDBApi.retrofitService.getRecipesByCategory(foodCategory.category).meals

                // TODO: Check for favourites against database ?
                
                _status.value = MealApiStatus.DONE
            } catch(e: Exception) {
                _status.value = MealApiStatus.ERROR
                _recipesList.value = null
            }
        }
    }
}