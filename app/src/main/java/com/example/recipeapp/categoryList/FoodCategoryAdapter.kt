package com.example.recipeapp.categoryList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.databinding.GridFoodCategoryItemBinding
import com.example.recipeapp.network.FoodCategory


/**
 * This class implements a RecyclerView ListAdapter which uses Data Binding to present a List
 * data, including computing diffs between lists.
 */
class FoodCategoryAdapter(private val onClickListener: OnClickListener):
    ListAdapter<FoodCategory, FoodCategoryAdapter.FoodCategoryViewHolder>(DiffCallback) {

    /**
     * Takes the binding variable from the grid view item and give it
     * the FoodCategory information
     */
    class FoodCategoryViewHolder(private var binding: GridFoodCategoryItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(foodCategory: FoodCategory) {
            binding.category = foodCategory
            binding.executePendingBindings()
        }
    }

    /**
     * Lets the RecyclerView figure out which items in the list has changed whenever the
     * FoodCategory list updates
     */
    companion object DiffCallback : DiffUtil.ItemCallback<FoodCategory>() {
        override fun areItemsTheSame(oldItem: FoodCategory, newItem: FoodCategory): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: FoodCategory, newItem: FoodCategory): Boolean {
            return oldItem.idCategory == newItem.idCategory
        }

    }

    /**
     * Creates a new RecyclerView item views
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodCategoryViewHolder {
        return FoodCategoryViewHolder(GridFoodCategoryItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    /**
     * Replaces the contents of the grid item with new information
     */
    override fun onBindViewHolder(holder: FoodCategoryViewHolder, position: Int) {
        val foodCategory = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(foodCategory)
        }
        holder.bind(foodCategory)
    }

    /**
     * Custom click listener to handle when the user clicks on a grid item
     */
    class OnClickListener(val clickListener: (foodCategory: FoodCategory) -> Unit) {
        fun onClick(foodCategory: FoodCategory) {
            clickListener(foodCategory)
        }
    }

}