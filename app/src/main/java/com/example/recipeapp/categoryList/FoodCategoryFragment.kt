package com.example.recipeapp.categoryList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.recipeapp.databinding.FragmentFoodCategoryBinding
import com.example.recipeapp.home.HomeFragmentDirections

/**
 * This fragment is used to show the different food categories
 */
class FoodCategoryFragment : Fragment() {
    /**
     * Lazy initialize FoodCategoryViewModel
     */
    val viewModel: FoodCategoryViewModel by lazy {
        ViewModelProvider(this).get(FoodCategoryViewModel::class.java)
    }

    /**
     * Inflates layout with Data Binding, set lifecycleOwner to the FoodCategoryFragment
     * allowing Data Binding to observe LiveData, and sets up the recyclerview
     * (categoryGrid) with an adapter
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding = FragmentFoodCategoryBinding.inflate(inflater)
        binding.lifecycleOwner = this

        // Giving the binding access to the FoodCategoryViewModel
        binding.categoriesViewModel = viewModel

        // Setting up recyclerview with an adapter
        binding.categoryGrid.adapter = FoodCategoryAdapter(FoodCategoryAdapter.OnClickListener {
            it?.let {
                this.findNavController()
                    .navigate(HomeFragmentDirections.actionHomeFragmentToRecipesFragment(it))

                viewModel.displaySelectedCategoryComplete()
            }
        })
        return binding.root
    }
}