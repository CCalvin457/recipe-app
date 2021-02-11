package com.example.recipeapp.youtube

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.FrameLayout
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.recipeapp.R
import com.example.recipeapp.databinding.FragmentYoutubeBinding
import com.example.recipeapp.utils.FullScreenHelper
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerFullScreenListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Suppress("DEPRECATION")
class YoutubeFragment : Fragment() {
    private lateinit var _appCompatActivity: AppCompatActivity
    private lateinit var _viewModel: YoutubeViewModel
    private lateinit var _youtubePlayer: YouTubePlayerView
    private lateinit var _frameLayout: FrameLayout
    val fullScreenHelper: FullScreenHelper by lazy {
        FullScreenHelper(activity)
    }

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
        _viewModel = ViewModelProvider(this, viewModelFactory)
            .get(YoutubeViewModel::class.java)

        _youtubePlayer = binding.recipeVideoYoutube
        _frameLayout = binding.videoLayout


        _youtubePlayer.getPlayerUiController().setFullScreenButtonClickListener {
            Log.d("YoutubeFragment", "Full screen button clicked, ${_viewModel.isFullScreen.value}")
            if (_viewModel.isFullScreen.value == true) {
//                _youtubePlayer.exitFullScreen()
                _viewModel.exitFullScreen()
                fullScreenHelper.exitFullScreen()
                _appCompatActivity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
//                val viewParams = _youtubePlayer.layoutParams
//                viewParams.height = ViewGroup.LayoutParams.MATCH_PARENT
//                viewParams.width = ViewGroup.LayoutParams.MATCH_PARENT
//                _youtubePlayer.layoutParams = viewParams
            } else {
//                _youtubePlayer.enterFullScreen()
                _viewModel.enterFullScreen()
                fullScreenHelper.enterFullScreen()
                _appCompatActivity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            }
        }

//        _viewModel.isFullScreen.observe(viewLifecycleOwner, {
//            if(it) {
//                enterFullScreen()
//                _viewModel.enterFullScreen()
//            } else {
//                exitFullScreen()
//                _viewModel.exitFullScreen()
//            }
//        })

        initYouTubePlayerView(_viewModel.videoId)

        return binding.root
    }


    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        _youtubePlayer.getPlayerUiController().getMenu()?.dismiss()
    }

    private fun initYouTubePlayerView(videoId: String) {
        initYouTubePlayerMenu()

        lifecycle.addObserver(_youtubePlayer)

        _youtubePlayer.addYouTubePlayerListener(object: AbstractYouTubePlayerListener() {
            override fun onReady(@NonNull youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                youTubePlayer.loadVideo(videoId, 0f)
                addFullScreenListenerToPlayer()
            }
        })
        Log.d("YoutubeFragment", "Exiting full screen. Width: ${_youtubePlayer.width}, Height: ${_youtubePlayer.height}, Frame Width: ${_frameLayout.width}, Frame Height: ${_frameLayout.height}")
    }

    private fun initYouTubePlayerMenu() {
        _youtubePlayer.getPlayerUiController()
                .showBufferingProgress(true)
                .showYouTubeButton(false)
    }

    private fun addFullScreenListenerToPlayer() {
        _youtubePlayer.addFullScreenListener(object: YouTubePlayerFullScreenListener {
            override fun onYouTubePlayerEnterFullScreen() {
                _appCompatActivity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                fullScreenHelper.enterFullScreen()
                Log.d("YoutubeFragment", "Exiting full screen. Width: ${_youtubePlayer.width}, Height: ${_youtubePlayer.height}, Frame Width: ${_frameLayout.width}, Frame Height: ${_frameLayout.height}")
            }

            override fun onYouTubePlayerExitFullScreen() {
                _appCompatActivity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                fullScreenHelper.exitFullScreen()
                Log.d("YoutubeFragment", "Exiting full screen. Width: ${_youtubePlayer.width}, Height: ${_youtubePlayer.height}, Frame Width: ${_frameLayout.width}, Frame Height: ${_frameLayout.height}")
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _appCompatActivity.supportActionBar?.show()
        _appCompatActivity.window.statusBarColor = resources.getColor(R.color.design_default_color_primary_dark)
    }

}