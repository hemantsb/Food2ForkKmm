package bit.hemant.kmmfood2fork.interactors.recipe_detail

import bit.hemant.kmmfood2fork.datasource.cache.RecipeCache
import bit.hemant.kmmfood2fork.domain.model.GenericMessageInfo
import bit.hemant.kmmfood2fork.domain.model.Recipe
import bit.hemant.kmmfood2fork.domain.model.UiComponentType
import bit.hemant.kmmfood2fork.domain.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

//
///**
// * Retrieve a recipe from the cache given it's unique id.
// */
//class GetRecipeUseCase(
//    private val recipeCache: RecipeCache, // We will change this to cache later
//) {
//    fun execute(
//        recipeId: Int,
//    ): Flow<DataState<Recipe?>> = flow {
//        try {
//            emit(DataState.Loading)
//            val recipe = recipeCache.get(recipeId)
//            emit(DataState.Data(data = recipe))
//        } catch (e: Exception) {
//            emit(DataState.ErrorMessage(e.message ?: "Unknown Error"))
//        }
//    }
//
//}

/**
 * Retrieve a recipe from the cache given it's unique id.
 */
class GetRecipe(
    private val recipeCache: RecipeCache,
) {
    fun execute(
        recipeId: Int,
    ): Flow<DataState<Recipe>> = flow {
        try {
            emit(DataState.loading())

            // for testing
            delay(1000)

            val recipe = recipeCache.get(recipeId)

            emit(DataState.data(message = null, data = recipe))

        } catch (e: Exception) {
            emit(
                DataState.error<Recipe>(
                    message = GenericMessageInfo.Builder()
                        .id("SearchRecipes.Error")
                        .title("Error")
                        .uiComponentType(UiComponentType.Dialog)
                        .description(e.message ?: "Unknown Error")
                )
            )
//            emit(DataState.error<Recipe>(message = e.message ?: "Unknown Error"))
        }
    }

}
