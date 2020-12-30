package com.example.recipeapp.favourites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.database.DatabaseRecipe
import com.example.recipeapp.database.FoodDatabaseDao
import com.example.recipeapp.utils.MealDatabaseStatus
import kotlinx.coroutines.launch

class FavouritesViewModel(dataSource: FoodDatabaseDao): ViewModel() {
    val database = dataSource

    private val _favouritesList = MutableLiveData<List<DatabaseRecipe>>()
    val favouritesList: LiveData<List<DatabaseRecipe>>
        get() = _favouritesList

    private val _status = MutableLiveData<MealDatabaseStatus>()
    val status: LiveData<MealDatabaseStatus>
        get() = _status

    init {
        getFavouritesList()
    }

    private fun getFavouritesList() {
        _status.value = MealDatabaseStatus.LOADING
        viewModelScope.launch {
            try {
                _favouritesList.value = database.getFavourites()
                _status.value = MealDatabaseStatus.DONE
            } catch(e: Exception) {
                _favouritesList.value = null
                _status.value = MealDatabaseStatus.ERROR
            }
        }

    }
}