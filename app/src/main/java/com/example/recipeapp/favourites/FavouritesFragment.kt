package com.example.recipeapp.favourites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.recipeapp.R
import com.example.recipeapp.database.FoodDatabase
import com.example.recipeapp.databinding.FragmentFavouritesBinding

class FavouritesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val application = requireNotNull(this.activity).application
        val dataSource = FoodDatabase.getInstance(application).foodDatabaseDao

        val binding = FragmentFavouritesBinding.inflate(inflater)

        val viewModelFactory = FavouritesViewModelFactory(dataSource)

        binding.favouritesViewModel = ViewModelProvider(this, viewModelFactory)
            .get(FavouritesViewModel::class.java)

        binding.lifecycleOwner = this



        return binding.root
    }

}