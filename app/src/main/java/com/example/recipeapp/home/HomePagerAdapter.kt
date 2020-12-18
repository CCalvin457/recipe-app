package com.example.recipeapp.home

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.recipeapp.favourites.FavouritesFragment
import com.example.recipeapp.recipeList.FoodCategoryFragment

class HomePagerAdapter(private val context: Context, fm: FragmentManager, private val totalTabs: Int):
        FragmentPagerAdapter(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getCount(): Int {
        return totalTabs
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> FoodCategoryFragment()
            1 -> FavouritesFragment()
            else -> getItem(position)
        }
    }
}