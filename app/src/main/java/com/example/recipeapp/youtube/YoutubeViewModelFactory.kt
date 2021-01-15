package com.example.recipeapp.youtube

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class YoutubeViewModelFactory(private val videoId: String): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(YoutubeViewModel::class.java)) {
            return YoutubeViewModel(videoId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}