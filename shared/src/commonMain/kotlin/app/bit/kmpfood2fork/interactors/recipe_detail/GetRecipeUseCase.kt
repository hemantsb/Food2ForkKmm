package app.bit.kmpfood2fork.interactors.recipe_detail

import app.bit.kmpfood2fork.datasource.network.RecipeService
import app.bit.kmpfood2fork.domain.model.Recipe
import app.bit.kmpfood2fork.domain.util.DataState
import app.bit.kmpfood2fork.domain.util.data
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Retrieve a recipe from the cache given it's unique id.
 */
class GetRecipeUseCase(
    private val recipeService: RecipeService, // We will change this to cache later
) {
    fun execute(
        recipeId: Int,
    ): Flow<DataState<Recipe>> = flow {
        try {
            emit(DataState.Loading)
            val recipe = recipeService.get(recipeId)
            emit(DataState.Data(data = recipe))
        } catch (e: Exception) {
            emit(DataState.ErrorMessage(e.message ?: "Unknown Error"))
        }
    }

}