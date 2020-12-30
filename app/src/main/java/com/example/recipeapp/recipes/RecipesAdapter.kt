package com.example.recipeapp.recipes

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

    class RecipesViewHolder (private var binding: ListRecipesItemBinding,
                             private val database: FoodDatabaseDao,
                             private val scope: CoroutineScope):
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup, database: FoodDatabaseDao, scope: CoroutineScope): RecipesViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListRecipesItemBinding
                    .inflate(layoutInflater, parent, false)

                return RecipesViewHolder(binding, database, scope)
            }
        }

        fun bind(recipe: Recipe) {
            binding.favourite.isChecked = recipe.isFavourite
            binding.recipe = recipe
            binding.favourite.setOnClickListener {
                if(binding.favourite.isChecked) {
                    val favouriteRecipe = DatabaseRecipe(
                        recipeId = recipe.id,
                        recipeName = recipe.name,
                        thumbnail = recipe.thumbnail)
                    insert(favouriteRecipe)
                } else {
                    remove(recipe.id)
                }
            }
            binding.executePendingBindings()
        }

        private fun insert(recipe: DatabaseRecipe) {
            scope.launch {
                database.insert(recipe)
            }
        }

        private fun remove(recipeId: Int) {
            scope.launch {
                val favouriteRecipe = database.getFavouriteRecipeByRecipeId(recipeId)
                database.delete(favouriteRecipe)
            }
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
        return RecipesViewHolder.from(parent, dataSource, scope)
    }

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        val recipe = getItem(position)
        holder.bind(recipe)
    }
}