package com.example.recipeapp.network

import com.squareup.moshi.FromJson
import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberProperties

class RecipeDetailsAdapter {
    @FromJson
    fun recipeFromJson(recipes: Recipes): RecipeDetails  {
        var ingredientsList: List<Ingredient>
        var recipeDetails: RecipeDetails
        val objectProps = MealDBRecipeDetails::class.memberProperties
        val ingredients = objectProps.filter { prop ->
            prop.name.contains("ingredient")
        }
        val measurements = objectProps.filter { prop ->
            prop.name.contains("measure")
        }

        ingredientsList = createIngredientsList(ingredients, measurements, recipes.meals[0])

        recipeDetails = createRecipeDetails(recipes.meals[0], ingredientsList)

        return recipeDetails
    }

    private fun createIngredientsList(ingredients: List<KProperty1<MealDBRecipeDetails, *>>,
                              measurements: List<KProperty1<MealDBRecipeDetails, *>>,
                              meal: MealDBRecipeDetails): List<Ingredient> {
        val ingredientsList = mutableListOf<Ingredient>()

        for((index, ingredient) in ingredients.withIndex()) {
            if(!ingredient.get(meal)?.toString().isNullOrBlank()) {
                val tempIngredient = Ingredient(ingredient.get(meal).toString(),
                    measurements[index].get(meal).toString())
                ingredientsList.add(tempIngredient)
            }
        }

        return ingredientsList
    }

    private fun createRecipeDetails(meal: MealDBRecipeDetails, ingredientsList: List<Ingredient>): RecipeDetails {
        return RecipeDetails(meal.id,
            meal.name,
            meal.drinkAlt,
            meal.category,
            meal.area,
            meal.instructions,
            meal.thumbnail,
            meal.tags,
            meal.video,
            ingredientsList,
            meal.source,
            meal.dateModified)
    }
}