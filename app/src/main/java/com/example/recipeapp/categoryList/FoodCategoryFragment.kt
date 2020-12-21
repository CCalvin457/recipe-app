package com.example.recipeapp.categoryList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.recipeapp.databinding.FragmentFoodCategoryBinding

class FoodCategoryFragment : Fragment() {
    val viewModel: FoodCategoryViewModel by lazy {
        ViewModelProvider(this).get(FoodCategoryViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding = FragmentFoodCategoryBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.recipeListViewModel = viewModel

        binding.categoryGrid.adapter = FoodCategoryAdapter(FoodCategoryAdapter.OnClickListener {

        })
        return binding.root
    }
}