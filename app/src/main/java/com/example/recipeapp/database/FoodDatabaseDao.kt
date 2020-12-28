package com.example.recipeapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface FoodDatabaseDao {
    @Insert
    suspend fun insert(recipe: Recipe)

    @Update
    suspend fun update(recipe: Recipe)

    @Query("SELECT * FROM recipe_table")
    suspend fun getFavourites(): LiveData<List<Recipe>>
}