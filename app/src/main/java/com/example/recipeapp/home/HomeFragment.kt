package com.example.recipeapp.home

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.viewpager.widget.ViewPager
import com.example.recipeapp.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayout

class HomeFragment : Fragment() {
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentHomeBinding.inflate(inflater)
        binding.lifecycleOwner = this
        tabLayout = binding.tabs
        viewPager = binding.viewPager

        // Creating tabs
        tabLayout.addTab(tabLayout.newTab().setText("Categories"))
        tabLayout.addTab(tabLayout.newTab().setText("Favourites"))

        // Setting up adapter
        val adapter = HomePagerAdapter(this.requireContext(), childFragmentManager)
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        return binding.root
    }
}