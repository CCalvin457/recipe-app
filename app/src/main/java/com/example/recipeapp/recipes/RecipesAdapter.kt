package com.example.recipeapp.recipes

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.database.DatabaseRecipe
import com.example.recipeapp.database.FoodDatabaseDao
import com.example.recipeapp.databinding.ListRecipesItemBinding
import com.example.recipeapp.network.Recipe
import kotlinx.coroutines.*

class RecipesAdapter(private val dataSource: FoodDatabaseDao, private val scope: CoroutineScope):
    ListAdapter<Recipe, RecipesAdapter.RecipesViewHolder>(RecipesDiffCallback) {
    class RecipesViewHolder (private var binding: ListRecipesItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): RecipesViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListRecipesItemBinding
                    .inflate(layoutInflater, parent, false)

                return RecipesViewHolder(binding)
            }
        }

        fun bind(recipe: Recipe, database: FoodDatabaseDao, scope: CoroutineScope) {
            binding.favourite.isChecked = recipe.isFavourite
            binding.recipe = recipe
            binding.favourite.setOnClickListener {
                if(binding.favourite.isChecked) {
                    Log.d("RecipeAdapter", "is Checked")
                    //TODO: add recipe to database
                    val favouriteRecipe = DatabaseRecipe()
                    scope.launch {
                        Log.d("RecipeAdapter", "in scope")
                        favouriteRecipe.recipeId = recipe.id
                        favouriteRecipe.recipeName = recipe.name
                        favouriteRecipe.thumbnail = recipe.thumbnail
                        database.insert(favouriteRecipe)
                    }
                } else {
                    Log.d("RecipeAdapter", "is NOT Checked")
                    //TODO: remove recipe from database
                    scope.launch {
                        val favouriteRecipe = database.getFavouriteRecipe(recipe.id)
                        database.delete(favouriteRecipe)
                    }
                }
            }
            binding.executePendingBindings()
        }
    }

    companion object RecipesDiffCallback: DiffUtil.ItemCallback<Recipe>() {
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        return RecipesViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        val recipe = getItem(position)
        holder.bind(recipe, dataSource, scope)
    }
}