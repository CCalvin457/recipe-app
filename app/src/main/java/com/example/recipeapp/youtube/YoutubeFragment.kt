package com.example.recipeapp.youtube

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.recipeapp.R
import com.example.recipeapp.databinding.FragmentYoutubeBinding

class YoutubeFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val videoId = YoutubeFragmentArgs.fromBundle(requireArguments()).videoId


        // Inflate the layout for this fragment
        val binding = FragmentYoutubeBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val viewModelFactory = YoutubeViewModelFactory(videoId)
        val viewModel = ViewModelProvider(this, viewModelFactory)
            .get(YoutubeViewModel::class.java)

        binding.youtubeViewModel = viewModel

        return binding.root
    }

}