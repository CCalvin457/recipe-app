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

enum class MealApiStatus { LOADING, ERROR, DONE }

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

    private fun getFoodCategories() {
        Log.d("FoodCategoryViewModel", "before viewModelScope")
        viewModelScope.launch {
            _status.value = MealApiStatus.LOADING
            Log.d("FoodCategoryViewModel", "Loading")
            try {
                _categories.value = MealDBApi.retrofitService.getCategories().categories
                Log.d("FoodCategoryViewModel", "${_categories.value}")
                _status.value = MealApiStatus.DONE
            } catch (e: Exception) {
                Log.d("FoodCategoryViewModel", "Error: ${e.message}")
                _status.value = MealApiStatus.ERROR
                _categories.value = null
            }
        }
    }
}