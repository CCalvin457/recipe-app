package com.example.recipeapp.recipes

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recipeapp.network.FoodCategory
import java.lang.IllegalArgumentException

class RecipesViewModelFactory(private val foodCategory: FoodCategory): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(RecipesViewModel::class.java)) {
            return RecipesViewModel(foodCategory) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}