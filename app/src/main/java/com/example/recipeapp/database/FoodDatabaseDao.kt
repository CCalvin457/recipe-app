package com.example.recipeapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FoodDatabaseDao {
    @Insert
    suspend fun insert(databaseRecipe: DatabaseRecipe)

    @Update
    suspend fun update(databaseRecipe: DatabaseRecipe)

    @Delete
    suspend fun delete(databaseRecipe: DatabaseRecipe)

    @Query("SELECT * FROM recipe_table")
    suspend fun getFavourites(): List<DatabaseRecipe>

    @Query("SELECT * FROM recipe_table WHERE recipe_id = :key")
    suspend fun getFavouriteRecipeByRecipeId(key: Int): DatabaseRecipe
}