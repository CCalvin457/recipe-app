package com.example.recipeapp.utils

import android.util.Log

class YouTubeHelper {
    companion object {
        fun getId(url: String?): String? {
            if(!url.isNullOrBlank()) {
                return url.split("v=")[1]
            }
            return null
        }

        fun getThumbnail(url: String?): String? {
            if(!url.isNullOrBlank()) {
                val videoId = getId(url)
                return "https://img.youtube.com/vi/$videoId/0.jpg"
            }
            return null
        }
    }

}