package com.example.recipeapp.recipes

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.recipeapp.MainActivity
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

        // Set toolbar title
        (requireActivity() as AppCompatActivity).supportActionBar?.title = foodCategory.category

        val application = requireNotNull(this.activity).application
        val dataSource = FoodDatabase.getInstance(application).foodDatabaseDao

        val viewModelFactory = RecipesViewModelFactory(dataSource, foodCategory)
        // Inflate the layout for this fragment
        val binding = FragmentRecipesBinding.inflate(inflater)
        val viewModel =
            ViewModelProvider(this, viewModelFactory).get(RecipesViewModel::class.java)
        binding.lifecycleOwner = this

        binding.recipesViewModel = viewModel


        binding.recipesList.adapter = RecipesAdapter(RecipesAdapter.OnClickListner {
            viewModel.displayRecipeDetails(it)
        }, dataSource, scope)


        viewModel.navigateToSelectedRecipe.observe(viewLifecycleOwner, Observer {
            it?.let {
                this.findNavController()
                    .navigate(RecipesFragmentDirections
                        .actionRecipesFragmentToRecipeDetailsFragment(it.id))

                viewModel.displayRecipeDetailsCompleted()
            }
        })
        return binding.root
    }


    override fun onDestroy() {
        scope.cancel()
        super.onDestroy()
    }
}