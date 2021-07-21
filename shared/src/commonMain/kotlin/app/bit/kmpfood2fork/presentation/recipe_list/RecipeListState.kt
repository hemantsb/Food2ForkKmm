package app.bit.kmpfood2fork.presentation.recipe_list

import app.bit.kmpfood2fork.domain.model.Recipe


data class RecipeListState(
    val isLoading: Boolean = false,
    val page: Int = 1,
    val query: String = "",
    val recipes: List<Recipe> = listOf(),
)