package com.example.recipeapp.youtube

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.recipeapp.R
import com.example.recipeapp.databinding.FragmentYoutubeBinding

class YoutubeFragment : Fragment() {
    private lateinit var _appCompatActivity: AppCompatActivity
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val videoId = YoutubeFragmentArgs.fromBundle(requireArguments()).videoId

        // Setting up actionbar to mimic a full screen
        _appCompatActivity = (requireActivity() as AppCompatActivity)
        _appCompatActivity.supportActionBar?.hide()
        _appCompatActivity.window.statusBarColor = Color.BLACK

        // Inflate the layout for this fragment
        val binding = FragmentYoutubeBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val viewModelFactory = YoutubeViewModelFactory(videoId)
        val viewModel = ViewModelProvider(this, viewModelFactory)
            .get(YoutubeViewModel::class.java)

        binding.youtubeViewModel = viewModel

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _appCompatActivity.supportActionBar?.show()
        _appCompatActivity.window.statusBarColor = resources.getColor(R.color.design_default_color_primary_dark)
    }
}