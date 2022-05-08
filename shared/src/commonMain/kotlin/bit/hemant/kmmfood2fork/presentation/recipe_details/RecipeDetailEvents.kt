package bit.hemant.kmmfood2fork.presentation.recipe_details

import bit.hemant.kmmfood2fork.presentation.recipe_list.RecipeListEvent

sealed class RecipeDetailEvents {

    data class GetRecipe(val recipeId: Int): RecipeDetailEvents()
    object OnRemoveHeadMessageFromQueue : RecipeDetailEvents()
}