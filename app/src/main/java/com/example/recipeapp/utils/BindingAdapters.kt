package com.example.recipeapp

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.recipeapp.network.RecipeDetails
import com.example.recipeapp.utils.MealApiStatus

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUrl = imgUrl.toUri().buildUpon().scheme("https").build()

        // Glide is used to load the image from a url into an ImageView
        Glide.with(imgView.context).load(imgUrl)
            .apply(RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image))
            .into(imgView)
    }
}

@BindingAdapter("mealApiStatus")
fun bindStatus(statusImageView: ImageView, status: MealApiStatus?) {
    when(status) {
        MealApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        MealApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        MealApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

@BindingAdapter("recyclerViewData")
fun <T, LA: ListAdapter<T, RecyclerView.ViewHolder>>
        bindRecycleViewData(recyclerView: RecyclerView, data: List<T>?) {
    data?.let {
        val adapter = recyclerView.adapter as LA
        adapter.submitList(data)
    }
}
