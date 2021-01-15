package com.example.recipeapp.recipeDetails

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.recipeapp.databinding.FragmentRecipeDetailsBinding

@Suppress("DEPRECATION")
class RecipeDetailsFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val arguments = RecipeDetailsFragmentArgs.fromBundle(requireArguments())

        (requireActivity() as AppCompatActivity).supportActionBar?.title = arguments.recipeName

        // Inflate the layout for this fragment
        val binding = FragmentRecipeDetailsBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val viewModelFactory = RecipeDetailsViewModelFactory(arguments.recipeId)
        val viewModel = ViewModelProvider(this, viewModelFactory)
            .get(RecipeDetailsViewModel::class.java)

        binding.recipeDetailsViewModel = viewModel


        binding.recipeVideoLayout.setOnClickListener {
            Log.d("RecipeDetailsFragment", "click listener")
            viewModel.videoId.value?.let {
                viewModel.displayVideoView(it)
            }
        }

        viewModel.navigateToVideoView.observe(viewLifecycleOwner, {
            it?.let {
                this.findNavController().navigate(RecipeDetailsFragmentDirections
                    .actionRecipeDetailsFragmentToYoutubeFragment(it))

                viewModel.displayVideoViewCompleted()
            }
        })

//        val youtubePlayer = binding.recipeVideoYoutube
//
//        youtubePlayer.getPlayerUiController().setFullScreenButtonClickListener {
//            Log.d("RecipeDetailsFragment", "Fullscreen clicked")
//            if(youtubePlayer.isFullScreen()) {
//                youtubePlayer.exitFullScreen()
//                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
//                    activity?.let { activity ->
//                        activity.window?.let { window ->
//                            window.setDecorFitsSystemWindows(false)
//                            val controller = window.insetsController
//
//                            controller?.show(WindowInsets.Type.navigationBars())
//                        }
//                    }
//
//                } else {
//                    activity?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
//                }
//            } else {
//                youtubePlayer.enterFullScreen()
//                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
//                    Log.d("RecipeDetailsFragment", "FULLLLLLSCREEEEEN")
//                    activity?.let { activity ->
//                        activity.window?.let { window ->
//                            window.setDecorFitsSystemWindows(false)
//                            val controller = window.insetsController
//
//                            controller?.hide(WindowInsets.Type.navigationBars())
//                            controller?.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
//                        }
//                    }
//
//                } else {
//                    activity?.window?.decorView?.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
//                            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                            or View.SYSTEM_UI_FLAG_IMMERSIVE
//                            or View.SYSTEM_UI_FLAG_LOW_PROFILE
//                            or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
//                }
//            }
//        }

        return binding.root
    }
}