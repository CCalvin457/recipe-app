package com.example.recipeapp.recipeDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class RecipeDetailsViewModelFactory(private val recipeId: Int): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(RecipeDetailsViewModel::class.java)) {
            return RecipeDetailsViewModel(recipeId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}