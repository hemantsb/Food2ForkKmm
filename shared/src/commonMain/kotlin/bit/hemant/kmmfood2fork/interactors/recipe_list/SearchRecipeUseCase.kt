package bit.hemant.kmmfood2fork.interactors.recipe_list

import bit.hemant.kmmfood2fork.datasource.cache.RecipeCache
import bit.hemant.kmmfood2fork.datasource.network.RecipeService
import bit.hemant.kmmfood2fork.domain.model.Recipe
import bit.hemant.kmmfood2fork.domain.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRecipeUseCase(
    private val recipeService: RecipeService,
    private val recipeCache: RecipeCache
) {

    fun execute(page: Int, query: String): Flow<DataState<List<Recipe>>> = flow {
        emit(DataState.Loading)
        try {
            val recipes = recipeService.search(
                page = page,
                query = query,
            )
            recipeCache.insert(recipes)
            val cacheResult =if(query.isBlank()){
                recipeCache.getAll(page)
            }else{
                recipeCache.search(query,page)
            }
            emit(DataState.Data(cacheResult))
        } catch (e: Exception) {
            emit(DataState.ErrorMessage(e.message ?: "Unknown Error"))
        }

    }
}