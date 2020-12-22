package com.example.recipeapp

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.recipeapp.categoryList.FoodCategoryAdapter
import com.example.recipeapp.network.FoodCategory
import com.example.recipeapp.network.Recipe
import com.example.recipeapp.recipes.RecipesAdapter
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

@BindingAdapter("foodCategoryData")
fun bindFoodCateooryView(recyclerView: RecyclerView, data: List<FoodCategory>?) {
    data?.let {
        val adapter = recyclerView.adapter as FoodCategoryAdapter
        adapter.submitList(data)
    }
}


@BindingAdapter("recipesListData")
fun bindRecipesListView(recyclerView: RecyclerView, data: List<Recipe>?) {
    data?.let {
        val adapter = recyclerView.adapter as RecipesAdapter
        adapter.submitList(data)
    }
}