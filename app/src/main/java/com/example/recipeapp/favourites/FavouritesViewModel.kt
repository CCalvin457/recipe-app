package com.example.recipeapp.favourites

import androidx.lifecycle.ViewModel
import com.example.recipeapp.database.FoodDatabaseDao

class FavouritesViewModel(dataSource: FoodDatabaseDao): ViewModel() {
    val database = dataSource


}