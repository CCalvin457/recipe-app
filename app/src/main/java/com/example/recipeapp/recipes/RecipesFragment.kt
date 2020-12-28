package com.example.recipeapp.recipes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.recipeapp.R
import com.example.recipeapp.database.FoodDatabase
import com.example.recipeapp.databinding.FragmentRecipesBinding


/**
 * A simple [Fragment] subclass.
 * Use the [RecipesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecipesFragment : Fragment() {
//    val viewModel: RecipesViewModel by lazy {
//        ViewModelProvider(this, ).get(RecipesViewModel::class.java)
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val foodCategory = RecipesFragmentArgs.fromBundle(requireArguments()).selectedCategory

        val application = requireNotNull(this.activity).application
        val dataSource = FoodDatabase.getInstance(application).foodDatabaseDao

        val viewModelFactory = RecipesViewModelFactory(dataSource, foodCategory)
        // Inflate the layout for this fragment
        val binding = FragmentRecipesBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.recipesViewModel =
            ViewModelProvider(this, viewModelFactory).get(RecipesViewModel::class.java)

        binding.recipesList.adapter = RecipesAdapter(dataSource)
        return binding.root
    }

}