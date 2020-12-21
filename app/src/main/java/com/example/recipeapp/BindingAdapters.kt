package com.example.recipeapp

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.recipeapp.categoryList.FoodCategoryAdapter
import com.example.recipeapp.categoryList.MealApiStatus
import com.example.recipeapp.network.FoodCategory

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUrl = imgUrl.toUri().buildUpon().scheme("https").build()

        Glide.with(imgView.context).load(imgUrl)
            .apply(RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image))
            .into(imgView)
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<FoodCategory>?) {
    data?.let {
        val adapter = recyclerView.adapter as FoodCategoryAdapter
        adapter.submitList(data)
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