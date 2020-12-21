package com.example.recipeapp.categoryList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.network.FoodCategory
import com.example.recipeapp.network.MealDBApi
import kotlinx.coroutines.launch
import java.lang.Exception

/**
 * Used to keep track of the current status of the api call.
 * Used to give the user an indication depend on the status
 */
enum class MealApiStatus { LOADING, ERROR, DONE }

/**
 * the ViewModel attached to FoodCategoryFragment
 */
class FoodCategoryViewModel: ViewModel() {
    private val _categories = MutableLiveData<List<FoodCategory>>()
    val categories: LiveData<List<FoodCategory>>
        get() = _categories

    private val _status = MutableLiveData<MealApiStatus>()
    val status: LiveData<MealApiStatus>
        get() = _status

    init {
        getFoodCategories()
    }

    // Getting the food categories from the MealDBApi service
    // Sets the status depending on what is currently happening
    private fun getFoodCategories() {
        viewModelScope.launch {
            _status.value = MealApiStatus.LOADING
            try {
                _categories.value = MealDBApi.retrofitService.getCategories().categories
                _status.value = MealApiStatus.DONE
            } catch (e: Exception) {
                _status.value = MealApiStatus.ERROR
                _categories.value = null
            }
        }
    }
}