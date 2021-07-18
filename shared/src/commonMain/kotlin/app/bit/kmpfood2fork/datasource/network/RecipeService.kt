package app.bit.kmpfood2fork.datasource.network

import app.bit.kmpfood2fork.domain.model.Recipe


interface RecipeService {

    suspend fun search(
        page: Int,
        query: String,
    ): List<Recipe>

    suspend fun get(
        id: Int
    ): Recipe
}