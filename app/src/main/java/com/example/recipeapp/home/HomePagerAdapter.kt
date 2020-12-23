package com.example.recipeapp.home

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.recipeapp.favourites.FavouritesFragment
import com.example.recipeapp.categoryList.FoodCategoryFragment

class HomePagerAdapter(private val context: Context, fm: FragmentManager):
        FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getCount(): Int {
        return 2
    }

    // Calls the respective fragment depending on which tab is selected
    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> FoodCategoryFragment()
            1 -> FavouritesFragment()
            else -> getItem(position)
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            0 -> "Categories"
            1 -> "Favourites"
            else -> ""
        }
    }
}