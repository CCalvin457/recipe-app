package com.example.recipeapp.recipeDetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.network.MealDBApi
import com.example.recipeapp.network.RecipeDetails
import com.example.recipeapp.network.Recipes
import com.example.recipeapp.utils.MealApiStatus
import com.example.recipeapp.utils.YouTubeHelper
import com.squareup.moshi.JsonAdapter
import kotlinx.coroutines.launch

class RecipeDetailsViewModel(recipeId: Int): ViewModel() {
    private val _recipeDetails = MutableLiveData<RecipeDetails>()
    val recipeDetails: LiveData<RecipeDetails>
        get() = _recipeDetails

    private val _status = MutableLiveData<MealApiStatus>()
    val status: LiveData<MealApiStatus>
        get() = _status

    private val _videoId = MutableLiveData<String>()
    val videoId: LiveData<String>
        get() = _videoId

    private val _navigateToVideoView = MutableLiveData<String>()
    val navigateToVideoView: LiveData<String>
        get() = _navigateToVideoView

    init {
        getRecipe(recipeId)
    }

    private fun getRecipe(recipeId: Int) {
        _status.value = MealApiStatus.LOADING
        viewModelScope.launch {
            try {
                _recipeDetails.value = MealDBApi.retrofitService.getRecipe(recipeId)
                Log.d("RecipeDetailsViewModel", "${_recipeDetails.value}")
                getVideoId(_recipeDetails.value?.video)
                _status.value = MealApiStatus.DONE
            } catch(e: Exception) {
                _recipeDetails.value = null
                Log.d("RecipeDetailsViewModel", "ERROR: ${e.message}")
                _status.value = MealApiStatus.ERROR
            }
        }
    }

    private fun getVideoId(url: String?) {
        if(!url.isNullOrBlank()) {
            _videoId.value = YouTubeHelper.getId(url)
        }
    }

    fun displayVideoView(videoId: String) {
        _navigateToVideoView.value = videoId
    }

    fun displayVideoViewCompleted() {
        _navigateToVideoView.value = null
    }
}

