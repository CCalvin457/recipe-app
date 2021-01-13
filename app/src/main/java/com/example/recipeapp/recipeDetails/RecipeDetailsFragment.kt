package com.example.recipeapp.recipeDetails

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.recipeapp.databinding.FragmentRecipeDetailsBinding

@Suppress("DEPRECATION")
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

        val youtubePlayer = binding.recipeVideoYoutube

        youtubePlayer.getPlayerUiController().setFullScreenButtonClickListener {
            Log.d("RecipeDetailsFragment", "Fullscreen clicked")
            if(youtubePlayer.isFullScreen()) {
                youtubePlayer.exitFullScreen()
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
                    activity?.let { activity ->
                        activity.window?.let { window ->
                            window.setDecorFitsSystemWindows(false)
                            val controller = window.insetsController

                            controller?.show(WindowInsets.Type.navigationBars())
                        }
                    }

                } else {
                    activity?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
                }
            } else {
                youtubePlayer.enterFullScreen()
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
                    Log.d("RecipeDetailsFragment", "FULLLLLLSCREEEEEN")
                    activity?.let { activity ->
                        activity.window?.let { window ->
                            window.setDecorFitsSystemWindows(false)
                            val controller = window.insetsController

                            controller?.hide(WindowInsets.Type.navigationBars())
                            controller?.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                        }
                    }

                } else {
                    activity?.window?.decorView?.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_IMMERSIVE
                            or View.SYSTEM_UI_FLAG_LOW_PROFILE
                            or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
                }
            }
        }

        return binding.root
    }
}