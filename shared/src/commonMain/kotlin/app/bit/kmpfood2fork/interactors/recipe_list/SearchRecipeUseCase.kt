package app.bit.kmpfood2fork.interactors.recipe_list

import app.bit.kmpfood2fork.datasource.network.RecipeService
import app.bit.kmpfood2fork.domain.model.Recipe
import app.bit.kmpfood2fork.domain.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRecipeUseCase(private val recipeService: RecipeService) {

    fun execute(page: Int, query: String): Flow<DataState<List<Recipe>>> = flow {
        emit(DataState.Loading)
        try {
            val recipes = recipeService.search(
                page = page,
                query = query,
            )
            emit(DataState.Data(recipes))
        } catch (e: Exception) {
            emit(DataState.ErrorMessage(e.message ?: "Unknown Error"))
        }

    }
}