package com.example.recipeapp.utils

import android.annotation.SuppressLint
import android.net.Uri
import android.text.Html
import android.view.View
import android.widget.TextView
import android.widget.VideoView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import com.example.recipeapp.network.Ingredient
import com.example.recipeapp.network.RecipeDetails
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@SuppressLint("SetTextI18n")
@BindingAdapter("recipeDetailsSubtitle")
fun bindRecipeDetailsSubtitle(textView: TextView, recipe: RecipeDetails?) {
    recipe?.let{
        textView.text = "${recipe.area} | ${recipe.category}"
    }
}

@BindingAdapter("recipeDetailsSource")
fun bindSource(textView: TextView, source: String?) {
    source?.let {
        textView.text = "Source: $it"
    }
}

@BindingAdapter("recipeDetailsIngredients")
fun bindRecipeDetailsIngredients(textView: TextView, ingredients: List<Ingredient>?) {
    textView.text = "N/A"
    ingredients?.let {
        var ingredientList = "<ul>"

        it.forEach{ ingredient ->
            ingredientList += "<li>${ingredient.ingredient} (${ingredient.measurement})</li>"
        }

        ingredientList += "</ul>"


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            textView.text = Html.fromHtml(ingredientList, Html.FROM_HTML_SEPARATOR_LINE_BREAK_LIST_ITEM)
        } else {
            textView.text = HtmlCompat.fromHtml(ingredientList, HtmlCompat.FROM_HTML_SEPARATOR_LINE_BREAK_LIST_ITEM)
        }
    }
}

@BindingAdapter("recipeDetailsVideo")
fun bindRecipeDetailsVideo(videoView: VideoView, url: String?) {
    videoView.visibility = View.GONE
    url?.let {
        videoView.setVideoURI(Uri.parse(it))
        videoView.visibility = View.VISIBLE
    }
}

@BindingAdapter("recipeDetailsYoutube")
fun bindRecipeDetailsYoutube(youtubePlayer: YouTubePlayerView, url: String?) {
    url?.let {
        if(it.contains("youtube")) {
            youtubePlayer.visibility = View.VISIBLE
            youtubePlayer.getPlayerUiController().showFullscreenButton(true)
            youtubePlayer.getPlayerUiController().showYouTubeButton(false)
            youtubePlayer.addYouTubePlayerListener(object: AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    super.onReady(youTubePlayer)
                    val videoId = it.split("v=")[1]
                    youTubePlayer.cueVideo(videoId, 0f)
                }
            })
        }
    }
}
