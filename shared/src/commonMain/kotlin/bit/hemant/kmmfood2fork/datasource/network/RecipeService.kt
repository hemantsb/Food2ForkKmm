package bit.hemant.kmmfood2fork.datasource.network

import bit.hemant.kmmfood2fork.domain.model.Recipe


interface RecipeService {

    suspend fun search(
        page: Int,
        query: String,
    ): List<Recipe>

    suspend fun get(
        id: Int
    ): Recipe
}