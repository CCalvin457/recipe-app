package com.example.recipeapp.youtube

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class YoutubeViewModel(videoId: String): ViewModel() {
    val videoId = videoId

    private val _isFullScreen = MutableLiveData<Boolean>()
    val isFullScreen: LiveData<Boolean>
        get() = _isFullScreen

    val youtubeView = MutableLiveData<YouTubePlayerView>()

    init {
        _isFullScreen.value = false
    }

    private fun toggleFullScreen() {
        _isFullScreen.value = _isFullScreen.value != true
        Log.d("YoutubeViewModel", "${_isFullScreen.value}")
    }

    fun enterFullScreen() {
//        youtubeView.value?.let {
            Log.d("YoutubeViewModel", "enterFullScreen")
            toggleFullScreen()
//            it.enterFullScreen()
//        }
    }

    fun exitFullScreen() {
//        youtubeView.value?.let {
            Log.d("YoutubeViewModel", "enterFullScreen")
            toggleFullScreen()
//            it.exitFullScreen()
//        }
    }
}