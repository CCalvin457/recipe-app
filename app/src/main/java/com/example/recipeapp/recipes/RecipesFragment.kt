package com.example.recipeapp.recipes

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.recipeapp.R
import com.example.recipeapp.database.FoodDatabase
import com.example.recipeapp.databinding.FragmentRecipesBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel

class RecipesFragment : Fragment() {
//    val viewModel: RecipesViewModel by lazy {
//        ViewModelProvider(this, ).get(RecipesViewModel::class.java)
//    }
    val scope = CoroutineScope(Job() + Dispatchers.IO)
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

        binding.recipesList.adapter = RecipesAdapter(dataSource, scope)
        return binding.root
    }


    override fun onDestroy() {
        scope.cancel()
        super.onDestroy()
    }
}