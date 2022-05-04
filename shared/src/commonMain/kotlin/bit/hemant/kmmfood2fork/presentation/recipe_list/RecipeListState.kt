package bit.hemant.kmmfood2fork.presentation.recipe_list

import bit.hemant.kmmfood2fork.domain.model.Recipe


data class RecipeListState(
    val isLoading: Boolean = false,
    val page: Int = 1,
    val query: String = "",
    val selectCategoty: FoodCategory?=null,
    val recipes: List<Recipe> = listOf(),
)