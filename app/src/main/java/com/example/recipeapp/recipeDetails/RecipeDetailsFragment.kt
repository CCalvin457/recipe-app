package com.example.recipeapp.recipeDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.recipeapp.R
import com.example.recipeapp.databinding.FragmentRecipeDetailsBinding

class RecipeDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentRecipeDetailsBinding.inflate(inflater)
        binding.lifecycleOwner = this
        val recipeId = RecipeDetailsFragmentArgs.fromBundle(requireArguments()).recipeId
        val viewModelFactory = RecipeDetailsViewModelFactory(recipeId)

        binding.recipeDetailsViewModel =
            ViewModelProvider(this, viewModelFactory).get(RecipeDetailsViewModel::class.java)
        return binding.root
    }
}