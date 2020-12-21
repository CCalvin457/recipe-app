package com.example.recipeapp.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.Call

private const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface MealDBApiService {
    @GET("categories.php")
    suspend fun getCategories(): Categories

//    @GET("categories.php")
//    suspend fun getTest(): Call<String>


}

object MealDBApi {
    val retrofitService: MealDBApiService by lazy {
        retrofit.create(MealDBApiService::class.java)
    }
}