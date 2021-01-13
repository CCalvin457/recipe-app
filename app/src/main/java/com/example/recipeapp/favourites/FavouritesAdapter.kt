package com.example.recipeapp.favourites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.database.DatabaseRecipe
import com.example.recipeapp.databinding.ListFavouritesItemBinding
import com.example.recipeapp.network.Recipe
import com.example.recipeapp.recipes.RecipesAdapter

class FavouritesAdapter(private val onClickListener: FavouritesAdapter.OnClickListner): ListAdapter<DatabaseRecipe, FavouritesAdapter.FavouritesViewHolder>(FavouritesDiffCallback) {

    class FavouritesViewHolder(private var binding: ListFavouritesItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): FavouritesViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListFavouritesItemBinding.inflate(layoutInflater, parent, false)

                return FavouritesViewHolder(binding)
            }
        }

        fun bind(databaseRecipe: DatabaseRecipe) {
            binding.recipe = databaseRecipe
            binding.executePendingBindings()
        }
    }

    companion object FavouritesDiffCallback: DiffUtil.ItemCallback<DatabaseRecipe>() {
        override fun areItemsTheSame(oldItem: DatabaseRecipe, newItem: DatabaseRecipe): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DatabaseRecipe, newItem: DatabaseRecipe): Boolean {
            return (oldItem.recipeId == newItem.recipeId) && (oldItem.id == newItem.id)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouritesViewHolder {
        return FavouritesViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: FavouritesViewHolder, position: Int) {
        val databaseRecipe = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(databaseRecipe)
        }
        holder.bind(databaseRecipe)
    }

    class OnClickListner(val clickListener: (recipe: DatabaseRecipe) -> Unit) {
        fun onClick(recipe: DatabaseRecipe) {
            clickListener(recipe)
        }
    }
}