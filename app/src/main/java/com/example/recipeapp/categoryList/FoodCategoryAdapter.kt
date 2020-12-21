package com.example.recipeapp.categoryList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.databinding.GridFoodCategoryItemBinding
import com.example.recipeapp.network.FoodCategory

class FoodCategoryAdapter(private val onClickListener: OnClickListener):
    ListAdapter<FoodCategory, FoodCategoryAdapter.FoodCategoryViewHolder>(DiffCallback) {

    class FoodCategoryViewHolder(private var binding: GridFoodCategoryItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(foodCategory: FoodCategory) {
            binding.category = foodCategory
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<FoodCategory>() {
        override fun areItemsTheSame(oldItem: FoodCategory, newItem: FoodCategory): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: FoodCategory, newItem: FoodCategory): Boolean {
            return oldItem.idCategory == newItem.idCategory
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodCategoryViewHolder {
        return FoodCategoryViewHolder(GridFoodCategoryItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: FoodCategoryViewHolder, position: Int) {
        val foodCategory = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(foodCategory)
        }
        holder.bind(foodCategory)
    }

    class OnClickListener(val clickListener: (foodCategory: FoodCategory) -> Unit) {
        fun onClick(foodCategory: FoodCategory) {
            clickListener(foodCategory)
        }
    }

}