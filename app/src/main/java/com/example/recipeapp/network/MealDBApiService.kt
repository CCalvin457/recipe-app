package com.example.recipeapp.network

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"

// Creating moshi instance
private val moshi = Moshi.Builder()
    .add(RecipeDetailsAdapter())
    .addLast(KotlinJsonAdapterFactory())
    .build()


// Creating retrofit instance
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()



/**
 * This interface defines how retrofit will talk to the api
 */
interface MealDBApiService {
    @GET("categories.php")
    suspend fun getCategories(): Categories

    @GET("filter.php")
    suspend fun getRecipesByCategory(@Query("c") category: String): Meals

    @GET("lookup.php")
    suspend fun getRecipe(@Query("i") id: Int): Recipes

}

/**
 * Initializing the retrofit service
 */
object MealDBApi {
    val retrofitService: MealDBApiService by lazy {
        retrofit.create(MealDBApiService::class.java)
    }
}