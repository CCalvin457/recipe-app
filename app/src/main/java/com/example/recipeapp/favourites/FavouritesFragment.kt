package com.example.recipeapp.favourites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.recipeapp.R
import com.example.recipeapp.database.FoodDatabase
import com.example.recipeapp.databinding.FragmentFavouritesBinding
import com.example.recipeapp.home.HomeFragmentDirections

class FavouritesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val application = requireNotNull(this.activity).application
        val dataSource = FoodDatabase.getInstance(application).foodDatabaseDao

        val binding = FragmentFavouritesBinding.inflate(inflater)

        val viewModelFactory = FavouritesViewModelFactory(dataSource)

        val viewModel = ViewModelProvider(this, viewModelFactory)
                .get(FavouritesViewModel::class.java)

        binding.favouritesViewModel = viewModel

        binding.lifecycleOwner = this

        binding.favouritesList.adapter = FavouritesAdapter(FavouritesAdapter.OnClickListner {
            viewModel.displayRecipeDetails(it)
        })

        viewModel.navigateToRecipeDetails.observe(viewLifecycleOwner, {
            it?.let {
                this.findNavController().navigate(HomeFragmentDirections
                        .actionHomeFragmentToRecipeDetailsFragment(it.recipeId, it.recipeName))

                viewModel.displayRecipeDetailsCompleted()
            }
        })

        return binding.root
    }

}