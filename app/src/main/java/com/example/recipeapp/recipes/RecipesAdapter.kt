package com.example.recipeapp.recipes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.databinding.ListRecipesItemBinding
import com.example.recipeapp.network.Recipe

class RecipesAdapter: ListAdapter<Recipe, RecipesAdapter.RecipesViewHolder>(RecipesDiffCallback) {
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

        fun bind(recipe: Recipe) {
            binding.recipe = recipe
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
        holder.bind(recipe)
    }
}