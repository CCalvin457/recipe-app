package com.example.recipeapp.utils

/**
 * Used to keep track of the current status of the database call.
 * Used to give the user an indication depend on the status
 */
enum class MealDatabaseStatus { LOADING, ERROR, DONE }