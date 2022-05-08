package bit.hemant.kmmfood2fork.interactors.recipe_list

import bit.hemant.kmmfood2fork.datasource.cache.RecipeCache
import bit.hemant.kmmfood2fork.datasource.network.RecipeService
import bit.hemant.kmmfood2fork.domain.model.GenericMessageInfo
import bit.hemant.kmmfood2fork.domain.model.Recipe
import bit.hemant.kmmfood2fork.domain.model.UiComponentType
import bit.hemant.kmmfood2fork.domain.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRecipeUseCase(
    private val recipeService: RecipeService,
    private val recipeCache: RecipeCache
) {

    fun execute(page: Int, query: String): Flow<DataState<List<Recipe>>> = flow {
        emit(DataState.loading())
        try {
            val recipes = recipeService.search(
                page = page,
                query = query,
            )
            if (query == "Error") {
                throw Exception("Forced error")
            }
            recipeCache.insert(recipes)
            val cacheResult = if (query.isBlank()) {
                recipeCache.getAll(page)
            } else {
                recipeCache.search(query, page)
            }
            emit(DataState.data<List<Recipe>>(message = null, data = cacheResult))
        } catch (e: Exception) {
            emit(
                DataState.error<List<Recipe>>(
                    message = GenericMessageInfo.Builder()
                        .id("SearchRecipes.Error")
                        .title("Error")
                        .uiComponentType(UiComponentType.Dialog)
                        .description(e.message ?: "Unknown Error")
                )
            )
        }

    }
}